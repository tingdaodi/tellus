package com.tellus.toolkit;

import cn.hutool.core.util.ArrayUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Optional;

/**
 * 类工具类
 * 1、扫描指定包下的所有类<br>
 * 参考 http://www.oschina.net/code/snippet_234657_22722
 *
 * @author seaside_hi, xiaoleilu, chill
 */
public class ClassKit {

    private ClassKit() {
        // 静态类不可实例化
    }

    /**
     * 获得ClassPath
     *
     * @return ClassPath
     */
    public static String getClassPath() {
        return getClassPathUrl().getPath();
    }

    /**
     * 获得ClassPath URL
     *
     * @return ClassPath URL
     */
    public static URL getClassPathUrl() {
        return getURL(CharsKit.EMPTY);
    }

    /**
     * 获得资源的URL
     *
     * @param resource 资源（相对Classpath的路径）
     * @return 资源URL
     */
    public static URL getURL(String resource) {
        return ClassKit.getClassLoader().getResource(resource);
    }


    /**
     * 是否为标准的类<br>
     * 这个类必须：<br>
     * 1、非接口 2、非抽象类 3、非Enum枚举 4、非数组 5、非注解 6、非原始类型（int, long等）
     *
     * @param clazz 类
     * @return 是否为标准类
     */
    public static boolean isNormalClass(Class<?> clazz) {
        return !clazz.isInterface() &&
                !isAbstract(clazz) &&
                !clazz.isEnum() &&
                !clazz.isArray() &&
                !clazz.isAnnotation() &&
                !clazz.isSynthetic() &&
                !clazz.isPrimitive();
    }

    /**
     * 是否为抽象类
     *
     * @param clazz 类
     * @return 是否为抽象类
     */
    public static boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    /**
     * 实例化对象
     *
     * @param clazz 类名
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> newInstance(String clazz) {
        try {
            return Optional.ofNullable((T) Class.forName(clazz).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(CharsKit.format("Instance class [{}] error!", clazz), e);
        }
    }

    /**
     * 实例化对象
     *
     * @param clazz 类
     * @return 对象
     */
    public static <T> Optional<T> newInstance(Class<T> clazz) {
        try {
            return Optional.ofNullable(clazz.newInstance());
        } catch (Exception e) {
            throw new RuntimeException(CharsKit.format("Instance class [{}] error!", clazz), e);
        }
    }

    /**
     * 实例化对象
     *
     * @param clazz 类
     * @return 对象
     */
    public static <T> Optional<T> newInstance(Class<T> clazz, Object... params) {
        if (ArrayUtil.isEmpty(params)) {
            return newInstance(clazz);
        }
        try {
            return Optional.of(clazz.getDeclaredConstructor(getClasses(params)).newInstance(params));
        } catch (Exception e) {
            throw new RuntimeException(CharsKit.format("Instance class [{}] error!", clazz), e);
        }
    }

    /**
     * 获得对象数组的类数组
     *
     * @param objects 对象数组
     * @return 类数组
     */
    public static Class<?>[] getClasses(Object... objects) {
        Class<?>[] classes = new Class<?>[objects.length];
        for (int i = 0; i < objects.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }

    /**
     * 检查目标类是否可以从原类转化<br>
     * 转化包括：<br>
     * 1、原类是对象，目标类型是原类型实现的接口<br>
     * 2、目标类型是原类型的父类<br>
     * 3、两者是原始类型或者包装类型（相互转换）
     *
     * @param targetType 目标类型
     * @param sourceType 原类型
     * @return 是否可转化
     */
    public static boolean isAssignable(Class<?> targetType, Class<?> sourceType) {
        // 对象类型
        if (targetType.isAssignableFrom(sourceType)) {
            return true;
        }

        // 基本类型
        if (targetType.isPrimitive()) {
            // 原始类型
            Class<?> resolvedPrimitive = BasicType.WRAPPER_PRIMITIVE_MAP.get(sourceType);
            return targetType.equals(resolvedPrimitive);
        } else {
            // 包装类型
            Class<?> resolvedWrapper = BasicType.PRIMITIVE_WRAPPER_MAP.get(sourceType);
            return resolvedWrapper != null && targetType.isAssignableFrom(resolvedWrapper);
        }
    }

    /**
     * 设置方法为可访问
     *
     * @param method 方法
     * @return 方法
     */
    public static Method setAccessible(Method method) {
        if (ClassKit.isNotPublic(method)) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * 指定类是否为非public
     *
     * @param clazz 类
     * @return 是否为非public
     */
    public static boolean isNotPublic(Class<?> clazz) {
        return !isPublic(clazz);
    }

    /**
     * 指定方法是否为非public
     *
     * @param method 方法
     * @return 是否为非public
     */
    public static boolean isNotPublic(Method method) {
        return !isPublic(method);
    }

    /**
     * 指定类是否为Public
     *
     * @param clazz 类
     * @return 是否为public
     */
    public static boolean isPublic(Class<?> clazz) {
        return Modifier.isPublic(clazz.getModifiers());
    }

    /**
     * 指定方法是否为Public
     *
     * @param method 方法
     * @return 是否为public
     */
    public static boolean isPublic(Method method) {
        return isPublic(method.getDeclaringClass());
    }

    /**
     * @return 当前线程的class loader
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获得class loader<br>
     * 若当前线程class loader不存在，取当前类的class loader
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (classLoader == null) {
            classLoader = ClassKit.class.getClassLoader();
        }
        return classLoader;
    }
}