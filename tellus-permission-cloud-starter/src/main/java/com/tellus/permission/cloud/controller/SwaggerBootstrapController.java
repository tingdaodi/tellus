package com.tellus.permission.cloud.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tellus.config.TellusGeneralProperties;
import com.tellus.config.redis.IRedisSyncLock;
import com.tellus.permission.TellusPermissionProperties;
import com.tellus.permission.cloud.service.IFieldService;
import com.tellus.permission.oauth2.support.UserDetailsUtils;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.enums.ParamMethodEnum;
import com.tellus.support.exception.AccessDeniedException;
import com.tellus.support.model.vo.InitFieldVO;
import com.tellus.toolkit.ResourceKit;
import io.swagger.models.ArrayModel;
import io.swagger.models.HttpMethod;
import io.swagger.models.Model;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.AbstractSerializableParameter;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;
import io.swagger.models.utils.PropertyModelConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApiIgnore
@Slf4j
@RestController
public class SwaggerBootstrapController {

    private static final String DEFAULT_GROUP_URL = "/field/source/data";
    private static final String ALL_GROUP_URL = "/field/source/data/all";

    //  swagger 分组名称
    private static final Set<String> GROUP_API_DOCS = Sets.newHashSet();

    private static final String PAGE_SELECT_MODEL_PREFIX = "PageInfo≪";
    private static final String PAGE_RESULT_MODEL_PREFIX = "PageWrapper≪";
    private static final String RESULT_MODEL_PREFIX = "Result≪";
    private static final String RESULT_NODE_MODEL_PREFIX = "Node≪";

    private final ServiceModelToSwagger2Mapper mapper;
    private final DocumentationCache documentationCache;
    private final IFieldService fieldService;
    private final TellusGeneralProperties tellusGeneralProperties;
    private final TellusPermissionProperties tellusPermissionProperties;


    @Autowired
    public SwaggerBootstrapController(ServiceModelToSwagger2Mapper mapper,
                                      DocumentationCache documentationCache,
                                      IFieldService fieldService,
                                      TellusGeneralProperties tellusGeneralProperties,
                                      TellusPermissionProperties tellusPermissionProperties) {
        this.mapper = mapper;
        this.documentationCache = documentationCache;
        this.fieldService = fieldService;
        this.tellusGeneralProperties = tellusGeneralProperties;
        this.tellusPermissionProperties = tellusPermissionProperties;
    }


    @IOperationLog(type = OperationTypeEnum.CREATED, theme = "初始化字段资源数据(ALL)", operator = "#{principal.name}")
    @IRedisSyncLock(value = "swagger_support:init_field_resources")
    @PostMapping(value = ALL_GROUP_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> generatedAll(@ApiIgnore @IOperationLog.Exclusion Principal principal) {
        if (!UserDetailsUtils.isSupperAdmin()) {
            throw new AccessDeniedException();
        }

        GROUP_API_DOCS.forEach(this::initField);

        return Result.success();
    }

    @IOperationLog(type = OperationTypeEnum.CREATED, theme = "初始化字段资源数据", operator = "#{}")
    @IRedisSyncLock(value = "swagger_support:init_field_resources")
    @PostMapping(value = DEFAULT_GROUP_URL, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Boolean> generated(@ApiIgnore @IOperationLog.Exclusion Principal principal,
                                     @RequestParam(value = "group", required = false) String swaggerGroup) {
        if (!UserDetailsUtils.isSupperAdmin()) {
            throw new AccessDeniedException();
        }
        String groupName = Optional.ofNullable(swaggerGroup).orElse(Docket.DEFAULT_GROUP_NAME);

        //  初始化字段资源数据
        this.initField(groupName);

        return Result.success();
    }

    private void initField(String groupName) {
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        Swagger swagger = mapper.mapDocumentation(documentation);
        Map<String, Model> definitions = swagger.getDefinitions();
        List<InitFieldVO> fields = Lists.newArrayList();

        swagger.getPaths().forEach((s, path) -> path.getOperationMap().forEach((httpMethod, operation) -> {
            //  PUT /roles/assign/fields/*
            String value = ResourceKit.builderValue(httpMethod.name(), s).replaceAll("\\{.*?\\}", "*");

            //  创建，删除，更新等接口，不需要生成字段数据
            //  InitFieldSpecialResources
            boolean isExclude = httpMethod == HttpMethod.DELETE
                    || httpMethod == HttpMethod.PUT
                    || (httpMethod == HttpMethod.POST && !value.endsWith("/page"));
            if (isExclude && !tellusPermissionProperties.getInitFieldSpecialResources().contains(value)) {
                return;
            }

            //  入参
            operation.getParameters().forEach(parameter -> {
                if (!(parameter instanceof HeaderParameter)) {
                    extractIncomingFields(value, parameter, definitions, fields);
                }
            });

            //  回参
            Model model = operation.getResponses().get("200").getResponseSchema();
            extractReturningFields(value, new PropertyModelConverter().modelToProperty(model), definitions, fields);
        }));

        if (!fields.isEmpty()) {
            this.fieldService.initFields(fields.stream()
                    .filter(r -> !Strings.isNullOrEmpty(r.getName()))
                    .collect(Collectors.toList()));
        }
    }

    private void extractIncomingFields(String value,
                                       Parameter parameter,
                                       Map<String, Model> definitions,
                                       List<InitFieldVO> fields) {
        ParamMethodEnum paramMethod = ParamMethodEnum.INCOMING;

        if (parameter instanceof AbstractSerializableParameter) {
            AbstractSerializableParameter<?> queryParameter = (AbstractSerializableParameter<?>) parameter;
            if (null == queryParameter.getItems()) {
                StringProperty stringProperty = new StringProperty();
                stringProperty.setName(queryParameter.getName());
                stringProperty.setDescription(queryParameter.getDescription());
                stringProperty.setType(queryParameter.getType());
                stringProperty.setExample(queryParameter.getExample());

                this.builders(paramMethod, value, stringProperty, definitions, fields);
            } else {
                this.builders(paramMethod, value, queryParameter.getItems(), definitions, fields);
            }
        }

        if (parameter instanceof BodyParameter) {
            BodyParameter bodyParameter = (BodyParameter) parameter;

            Model schema = bodyParameter.getSchema();
            if (schema instanceof ArrayModel) {
                Property temp = ((ArrayModel) schema).getItems();
                if (!(temp instanceof RefProperty) && !(temp instanceof ArrayProperty)) {
                    fields.add(bodyParameterToVO(paramMethod, value, bodyParameter));
                    return;
                }
            }

            Property property = new PropertyModelConverter().modelToProperty(schema);
            this.builders(paramMethod, value, property, definitions, fields);
        }


    }

    private void extractReturningFields(String value,
                                        Property property,
                                        Map<String, Model> definitions,
                                        List<InitFieldVO> fields) {
        ParamMethodEnum paramMethod = ParamMethodEnum.RETURN;
        this.builders(paramMethod, value, property, definitions, fields);
    }


    private InitFieldVO bodyParameterToVO(ParamMethodEnum paramMethod,
                                          String value,
                                          BodyParameter bodyParameter) {
        String label = null == bodyParameter.getDescription() ? bodyParameter.getName() : bodyParameter.getDescription();
        String example = String.valueOf(bodyParameter.getExamples());
        String remark = isEmpty(example) ? label : example;

        return InitFieldVO
                .builder()
                .resourceValue(value)
                .name(bodyParameter.getName())
                .label(label)
                .remark(remark)
                .type("array")
                .method(paramMethod)
                .enabled(Boolean.TRUE)
                .creator(tellusGeneralProperties.getSystem())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void builders(ParamMethodEnum paramMethod,
                          String value,
                          Property property,
                          Map<String, Model> definitions,
                          List<InitFieldVO> fields) {
        if (property instanceof RefProperty || property instanceof ArrayProperty) {
            RefProperty refProperty;
            if (property instanceof RefProperty) {
                refProperty = (RefProperty) property;
            } else {
                ArrayProperty arrayProperty = (ArrayProperty) property;
                Property temp = arrayProperty.getItems();

                //  非衍射类型， List<String>...
                //  数组特例处理
                if (!(temp instanceof RefProperty) && !(temp instanceof ArrayProperty)) {
                    fields.add(this.propertyToVo(paramMethod, value, arrayProperty));
                    return;
                }

                refProperty = (RefProperty) ((ArrayProperty) property).getItems();
            }

            String simpleRef = refProperty.getSimpleRef();
            Map<String, Property> properties = definitions.get(simpleRef).getProperties();

            //  分页查询参数
            if (simpleRef.startsWith(PAGE_SELECT_MODEL_PREFIX)) {
                simpleRef = ((RefProperty) properties.get("queries")).getSimpleRef();
            }
            //  分页返回参数
            if (simpleRef.startsWith(PAGE_RESULT_MODEL_PREFIX)) {
                simpleRef = ((RefProperty) ((ArrayProperty) properties.get("records")).getItems()).getSimpleRef();
            }
            //  Node
            if (simpleRef.startsWith(RESULT_NODE_MODEL_PREFIX)) {
                simpleRef = ((RefProperty) properties.get("sources")).getSimpleRef();
            }
            //  Result
            if (simpleRef.startsWith(RESULT_MODEL_PREFIX)) {
                Property property1 = properties.get("data");
                if (property1 instanceof RefProperty || property1 instanceof ArrayProperty) {
                    this.builders(paramMethod, value, property1, definitions, fields);
                }
                return;
            }

            definitions.get(simpleRef).getProperties().forEach((s, property1) -> {
                if (log.isDebugEnabled()) {
                    log.debug("Init builder: {}", property1);
                }
                this.builders(paramMethod, value, property1, definitions, fields);
            });
        } else {
            InitFieldVO fieldVO = this.propertyToVo(paramMethod, value, property);
            boolean contains = fields.contains(fieldVO);
            if (!contains) {
                fields.add(fieldVO);
            }
        }
    }

    private InitFieldVO propertyToVo(ParamMethodEnum paramMethodEnum,
                                     String value,
                                     Property property) {
        String label = null == property.getDescription() ? property.getName() : property.getDescription();
        String example = String.valueOf(property.getExample());
        String remark = isEmpty(example) ? label : example;
        String type = property.getType();

        if (property instanceof StringProperty) {
            StringProperty stringProperty = (StringProperty) property;
            if (null != stringProperty.getEnum() && !stringProperty.getEnum().isEmpty()) {
                type = "integer";
            }
        }

        return InitFieldVO
                .builder()
                .resourceValue(value)
                .label(label)
                .remark(remark)
                .type(type)
                .method(paramMethodEnum)
                .enabled(Boolean.TRUE)
                .creator(tellusGeneralProperties.getSystem())
                .createdAt(LocalDateTime.now())
                .build();

    }

    private boolean isEmpty(String value) {
        return "null".equals(value) || Strings.isNullOrEmpty(value);
    }

}
