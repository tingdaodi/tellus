package com.tellus.permission.oauth2.authorization;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.tellus.permission.oauth2.service.AuthorizationService;
import com.tellus.support.model.vo.result.ResourceVO;
import com.tellus.toolkit.ExceptionUtils;
import com.tellus.toolkit.ResourceKit;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 自定义: 加载所有权限数据, 以及获取当前登录用户拥有的权限
 *
 * @author Roy
 * @date 2020/7/9 13:43
 */
public class IFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final LoadingCache<String, List<ResourceVO>> CACHE_ALL_RESOURCES;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final WebMvcProperties webMvcProperties;
    private FilterInvocationSecurityMetadataSource supperMetadataSource;

    public IFilterInvocationSecurityMetadataSource(WebMvcProperties webMvcProperties,
                                                   AuthorizationService authorizationService) {
        this.webMvcProperties = webMvcProperties;

        String servletPath = webMvcProperties.getServlet().getPath();
        CACHE_ALL_RESOURCES = CacheBuilder.newBuilder()
                .refreshAfterWrite(2, TimeUnit.MINUTES)
                .build(new CacheLoader<String, List<ResourceVO>>() {
                    @Override
                    public List<ResourceVO> load(String key) throws Exception {
                        return authorizationService.findAllResources()
                                .stream()
                                .filter(resource -> !resource.getValue().isEmpty() && resource.getEnabled())
                                .peek(v -> {
                                    v.setValue(ResourceKit.replaceValue(v.getValue(), servletPath));
                                })
                                .map(ResourceVO::toPermission)
                                .collect(Collectors.toList());
                    }
                });
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String resourceValue = getResourceValue(fi);

        Set<ConfigAttribute> configAttributes = this.getConfigAttributes(resourceValue);
        if (CollectionUtil.isNotEmpty(configAttributes)) {
            return configAttributes;
        }
        return Stream.of(configAttributes, supperMetadataSource.getAttributes(object))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void setSupperMetadataSource(FilterInvocationSecurityMetadataSource supperMetadataSource) {
        this.supperMetadataSource = supperMetadataSource;
    }

    private Set<ConfigAttribute> getConfigAttributes(String resourceValue) {
        try {
            Map<String, String> resources = CACHE_ALL_RESOURCES.get("all")
                    .stream()
                    .collect(Collectors.toMap(ResourceVO::getValue, ResourceVO::getPermission,
                            (oldValue, newValue) -> newValue,
                            TreeMap::new));

            for (Map.Entry<String, String> entry : resources.entrySet()) {
                if (antPathMatcher.match(entry.getKey(), resourceValue)) {
                    return new HashSet<>(SecurityConfig.createList(entry.getValue()));
                }
            }
            return Collections.emptySet();
        } catch (ExecutionException e) {
            throw ExceptionUtils.mpe(e);
        }
    }

    private String getResourceValue(FilterInvocation filterInvocation) {
        return ResourceKit.builderValue(filterInvocation.getRequest().getMethod(), filterInvocation.getRequest().getRequestURI());
    }
}
