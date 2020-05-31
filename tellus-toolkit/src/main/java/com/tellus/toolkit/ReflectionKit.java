/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tellus.toolkit;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.google.common.base.Preconditions;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * 反射工具类，提供反射相关的快捷操作
 *
 * @author Caratacus
 * @author hcl
 * @since 2016-09-22
 */
public final class ReflectionKit {

    /**
     * class field cache
     */
    private static final Map<Class<?>, List<Field>> CLASS_FIELD_CACHE = new ConcurrentHashMap<>();

    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_TYPE_MAP = new IdentityHashMap<>(8);

    static {
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Character.class, char.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Double.class, double.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Float.class, float.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Integer.class, int.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Long.class, long.class);
        PRIMITIVE_WRAPPER_TYPE_MAP.put(Short.class, short.class);
    }

    /**
     * <p>
     * 获取 public get方法的值
     * </p>
     *
     * @param cls    ignore
     * @param entity 实体
     * @param str    属性字符串内容
     * @return Object
     */
    public static Object getMethodValue(Class<?> cls, Object entity, String str) {
        Map<String, Field> fieldMaps = getFieldMap(cls);

        Preconditions.checkArgument(CollectionUtil.isNotEmpty(fieldMaps),
                "Error: NoSuchField in %s for %s.  Cause:", cls.getSimpleName(), str);

        try {
            Method method = cls.getMethod(guessGetterName(fieldMaps.get(str), str));
            return method.invoke(entity);
        } catch (NoSuchMethodException e) {
            throw ExceptionUtils.mpe("Error: NoSuchMethod in %s.  Cause:", e, cls.getSimpleName());
        } catch (IllegalAccessException e) {
            throw ExceptionUtils.mpe("Error: Cannot execute a private method. in %s.  Cause:", e, cls.getSimpleName());
        } catch (InvocationTargetException e) {
            throw ExceptionUtils.mpe("Error: InvocationTargetException on getMethodValue.  Cause:" + e);
        }
    }

    /**
     * 猜测方法名
     *
     * @param field 字段
     * @param str   属性字符串内容
     */
    private static String guessGetterName(Field field, final String str) {
        return StringUtils.guessGetterName(str, field.getType());
    }

    /**
     * <p>
     * 获取 public get方法的值
     * </p>
     *
     * @param entity 实体
     * @param str    属性字符串内容
     * @return Object
     */
    public static Object getMethodValue(Object entity, String str) {
        if (null == entity) {
            return null;
        }
        return getMethodValue(entity.getClass(), entity, str);
    }

    /**
     * <p>
     * 获取 public get方法的值
     * </p>
     *
     * @param entity          实体
     * @param annotationClass 标注的注解
     * @return Object
     */
    public static Object getMethodValue(Object entity, Class<? extends Annotation> annotationClass) {
        Class<?> cls = entity.getClass();
        Field field = ReflectionKit.getUserAnnotationField(cls, annotationClass)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Could not" +
                        " find @%s in class: %s.", annotationClass.getSimpleName(), cls.getName()
                )));
        Method method = ReflectionKit.getMethod(cls, field);
        method.setAccessible(true);

        try {
            return method.invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(String.format("The field can't access in the class, "
                    + "class: %s", cls.getName()));
        }
    }

    /**
     * <p>
     * 反射对象获取泛型
     * </p>
     *
     * @param clazz 对象
     * @param index 泛型所在位置
     * @return Class
     */
    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            System.out.println(String.format("Warn: %s's superclass not ParameterizedType", clazz.getSimpleName()));
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            System.out.println(String.format("Warn: Index: %s, Size of %s's Parameterized Type: %s .", index,
                    clazz.getSimpleName(), params.length));
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            System.out.println(String.format("Warn: %s not set the actual class on superclass generic parameter",
                    clazz.getSimpleName()));
            return Object.class;
        }
        return (Class<?>) params[index];
    }

    /**
     * <p>
     * 获取该类的所有属性列表
     * </p>
     *
     * @param clazz 反射类
     */
    public static Map<String, Field> getFieldMap(Class<?> clazz) {
        List<Field> fieldList = getFieldList(clazz);
        return CollectionUtil.isNotEmpty(fieldList)
                ? fieldList.stream().collect(Collectors.toMap(Field::getName, field -> field))
                : Collections.emptyMap();
    }

    /**
     * <p>
     * 获取该类的所有属性列表
     * </p>
     *
     * @param clazz 反射类
     */
    public static List<Field> getFieldList(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }
        List<Field> fields = CLASS_FIELD_CACHE.get(clazz);
        if (CollectionUtil.isEmpty(fields)) {
            synchronized (CLASS_FIELD_CACHE) {
                fields = doGetFieldList(clazz);
                CLASS_FIELD_CACHE.put(clazz, fields);
            }
        }
        return fields;
    }

    /**
     * 获取标记注解的属性
     *
     * @param cls             反射类
     * @param annotationClass 注解类
     * @return 属性对象
     */
    public static Optional<Field> getUserAnnotationField(Class<?> cls, Class<? extends Annotation> annotationClass) {
        return getFieldList(cls)
                .stream()
                .filter(field -> field.isAnnotationPresent(annotationClass))
                .findFirst();
    }

    /**
     * 获取属性
     *
     * @param cls       反射类
     * @param fieldName 注解类
     * @return 属性对象
     */
    public static Optional<Field> getField(Class<?> cls, String fieldName) {
        Map<String, Field> fieldMap = getFieldMap(cls);
        Preconditions.checkArgument(CollectionUtil.isNotEmpty(fieldMap),
                "Error: NoSuchField in %s for %s. Cause:", cls.getSimpleName(), fieldName);
        return Optional.ofNullable(fieldMap.get(fieldName));
    }

    /**
     * <p>
     * 获取该类的所有属性列表
     * </p>
     *
     * @param clazz 反射类
     */
    public static List<Field> doGetFieldList(Class<?> clazz) {
        if (clazz.getSuperclass() != null) {
            /* 排除重载属性 */
            Map<String, Field> fieldMap = excludeOverrideSuperField(clazz.getDeclaredFields(),
                    /* 处理父类字段 */
                    getFieldList(clazz.getSuperclass()));
            List<Field> fieldList = new ArrayList<>();
            /*
             * 重写父类属性过滤后处理忽略部分，支持过滤父类属性功能
             * 场景：中间表不需要记录创建时间，忽略父类 createTime 公共属性
             * 中间表实体重写父类属性 ` private transient Date createTime; `
             */
            fieldMap.forEach((k, v) -> {
                /* 过滤静态属性 */
                if (!Modifier.isStatic(v.getModifiers())
                        /* 过滤 transient关键字修饰的属性 */
                        && !Modifier.isTransient(v.getModifiers())) {
                    fieldList.add(v);
                }
            });
            return fieldList;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * <p>
     * 排序重置父类属性
     * </p>
     *
     * @param fields         子类属性
     * @param superFieldList 父类属性
     */
    public static Map<String, Field> excludeOverrideSuperField(Field[] fields, List<Field> superFieldList) {
        // 子类属性
        Map<String, Field> fieldMap = Stream.of(fields).collect(toMap(Field::getName, identity(),
                (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                LinkedHashMap::new));
        superFieldList.stream().filter(field -> !fieldMap.containsKey(field.getName()))
                .forEach(f -> fieldMap.put(f.getName(), f));
        return fieldMap;
    }

    /**
     * 获取字段get方法
     *
     * @param cls   class
     * @param field 字段
     * @return Get方法
     */
    public static Method getMethod(Class<?> cls, Field field) {
        try {
            return cls.getDeclaredMethod(ReflectionKit.guessGetterName(field, field.getName()));
        } catch (NoSuchMethodException e) {
            throw ExceptionUtils.mpe("Error: NoSuchMethod in %s.  Cause:", e, cls.getName());
        }
    }

    /**
     * 获取字段get方法
     *
     * @param cls       class
     * @param fieldName 字段名
     * @return Get方法
     */
    public static Method getMethod(Class<?> cls, String fieldName) {
        try {
            return cls.getDeclaredMethod(StringUtils.guessGetterName(fieldName, cls));
        } catch (NoSuchMethodException e) {
            throw ExceptionUtils.mpe("Error: NoSuchMethod in %s, Cause:", e, cls.getName());
        }
    }

    /**
     * 判断是否为基本类型或基本包装类型
     *
     * @param clazz class
     * @return 是否基本类型或基本包装类型
     */
    public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return (clazz.isPrimitive() || PRIMITIVE_WRAPPER_TYPE_MAP.containsKey(clazz));
    }

}
