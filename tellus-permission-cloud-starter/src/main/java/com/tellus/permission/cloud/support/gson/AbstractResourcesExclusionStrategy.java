package com.tellus.permission.cloud.support.gson;

import com.google.common.collect.Sets;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.internal.Primitives;
import com.tellus.toolkit.util.SpringRequestUtils;
import org.apache.commons.lang3.ClassUtils;

import java.util.Objects;
import java.util.Set;

public abstract class AbstractResourcesExclusionStrategy implements ExclusionStrategy {

    // ~ Static fields/initializers
    // ========================================================================================================

    private final Set<String> NOT_EXCLUDE_FIELDS = Sets.newHashSet("id");
    private final ExclusionPathRequestMatcher exclusionPathRequestMatcher;

    // ~ Constructors
    // ========================================================================================================

    public AbstractResourcesExclusionStrategy(String ignoreUrls, String servletPath) {
        this.exclusionPathRequestMatcher = new ExclusionPathRequestMatcher(ignoreUrls, servletPath);
    }

    // ~ Override/Protected Methods
    // ========================================================================================================

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        //  return true 为排除
        //  忽略过滤的路由
        if (!exclusionPathRequestMatcher.matches(SpringRequestUtils.getRequest())) {
            return false;
        }

        // 忽略过滤的类型
        if (isTypeNotSupported(f.getDeclaringClass())) {
            return false;
        }

        //  非基础类型、基础类型的包装类型、枚举、String、忽略过滤
        if (!isPrimitivesOrWrapper(f.getDeclaringClass())) {
            return false;
        }

        //  {@code @IExpose} 标注注解的类、字段， 忽略过滤
        if (isExpose(f)) {
            return false;
        }

        Set<String> outputFields = getSupportedField();
        if (outputFields.isEmpty()) {
            return false;
        }

        //  特定不能排除的字段
        String name = f.getName();
        if (NOT_EXCLUDE_FIELDS.contains(name)) {
            return false;
        }
        return outputFields.stream().noneMatch(field -> Objects.equals(field, name));
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    /**
     * 是否为支持过滤的类类型
     *
     * @param clazz 类类型
     * @return boolean
     */
    protected abstract boolean isTypeNotSupported(Class<?> clazz);

    /**
     * 是否标注过滤 @IExpose 注解
     *
     * @param f Field 对象
     * @return boolean
     */
    protected abstract boolean isExpose(FieldAttributes f);

    /**
     * 可以输出的字段
     *
     * @return Set<String> 包含的字段不过滤
     */
    protected abstract Set<String> getSupportedField();

    /**
     * 是否为基础类型， 及其包装类型
     *
     * @param clazz 类型
     * @return boolean
     */
    private boolean isPrimitivesOrWrapper(Class<?> clazz) {
        return Primitives.isPrimitive(clazz)
                || Primitives.isWrapperType(clazz)
                || ClassUtils.isAssignable(clazz, String.class)
                || clazz.isEnum();
    }
}
