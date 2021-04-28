package com.tellus.permission.cloud.support.gson;

import com.google.gson.FieldAttributes;
import com.tellus.permission.cloud.support.FieldCacheUtils;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import com.tellus.support.PageInfo;
import com.tellus.support.PageWrapper;
import com.tellus.support.Result;
import com.tellus.support.annotation.IExpose;
import com.tellus.support.enums.ParamMethodEnum;
import com.tellus.toolkit.tree.Node;
import org.apache.commons.lang3.ClassUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class ResourcesDeserializationExclusionStrategy extends AbstractResourcesExclusionStrategy {

    // ~ Constructors
    // ========================================================================================================

    public ResourcesDeserializationExclusionStrategy(String ignoreUrls, String servletPath) {
        super(ignoreUrls, servletPath);
    }

    // ~ Override/Protected Methods
    // ========================================================================================================

    @Override
    protected boolean isTypeNotSupported(Class<?> clazz) {
        return ClassUtils.isAssignable(clazz, PageInfo.class)
                || ClassUtils.isAssignable(clazz, Result.class)
                || ClassUtils.isAssignable(clazz, PageWrapper.class)
                || ClassUtils.isAssignable(clazz, Node.class);
    }

    @Override
    protected boolean isExpose(FieldAttributes f) {
        IExpose exposeClass = f.getDeclaredClass().getAnnotation(IExpose.class);
        return null != exposeClass && exposeClass.deserialize();
    }

    @Override
    protected Set<String> getSupportedField() {
        return FieldCacheUtils.getFields()
                .stream()
                .filter(field -> field.getMethod() == ParamMethodEnum.INCOMING)
                .map(CustomizeUserDetails.Field::getName)
                .collect(Collectors.toSet());
    }
}
