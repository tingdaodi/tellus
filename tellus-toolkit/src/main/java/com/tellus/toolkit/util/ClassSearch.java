package com.tellus.toolkit.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 搜索class的工具类
 * <p>
 * 工程内部若需要对类进行搜索应该尽量使用该类。而不要依赖spring。避免spring版本冲突。
 *
 * @author ath
 */
public class ClassSearch {

    private static final Logger logger = LoggerFactory.getLogger(ClassSearch.class);

    /**
     * class文件和目录过滤
     */
    public static FileFilter CLASS_FILE_AND_DIR_FILTER = (File file) -> (file.isDirectory()) || (file.getName().endsWith(".class"));

    /**
     * 查找指定package下的所有类，该方法使用的加载Class方式不会初始化static。
     * 搜索时应该确保这样是安全的
     * 可以通过getClassNames() 获取所有Class的完整名称。然后自行处理。
     *
     * @param rootPackage 包路径
     * @param recursive   是否迭代查找
     * @param condition   查找条件
     * @throws ClassNotFoundException 通常抛出该异常的原因是，该类内部使用、继承、实现了一个无法在本工程和依赖项中找到的类或接口。
     */
    @SuppressWarnings("unchecked")
    public static <E> Collection<Class<E>> findClass(String rootPackage, boolean recursive, Predicate<Class> condition) throws ClassNotFoundException {
        Collection<String> classNames = getClassNames(rootPackage, recursive);

        Collection<Class<E>> classes = new ArrayList<>();
        for (String className : classNames) {
            Class<E> clazz = (Class<E>) Thread.currentThread().getContextClassLoader().loadClass(className);
            if (condition.test(clazz)) {
                classes.add(clazz);
            }
        }
        return classes;
    }

    /**
     * 安全的查找指定package下的所有类。
     * 为避免报错，可以通过getClassNames() 获取所有Class的完整名称。然后自行处理。
     *
     * @param rootPackage 包路径
     * @param recursive   是否迭代查找
     * @param condition   查找条件
     * @throws ClassNotFoundException 通常抛出这个异常的原因是static变量无法初始化，或class内部使用、继承、实现的类或接口无法在本工程和依赖项中找到。
     */
    @SuppressWarnings("unchecked")
    public static <E> Collection<Class<E>> safeFindClass(String rootPackage, boolean recursive, Predicate<Class> condition) throws ClassNotFoundException {
        Collection<String> classNames = getClassNames(rootPackage, recursive);

        Collection<Class<E>> classes = new ArrayList<>();
        for (String className : classNames) {
            Class<E> clazz = (Class<E>) Class.forName(className);
            if (condition.test(clazz)) {
                classes.add(clazz);
            }
        }
        return classes;
    }

    /**
     * 不安全的搜索方式，遇到无法加载的类型，直接跳过。
     */
    @SuppressWarnings("unchecked")
    public static <E> Collection<Class<E>> unSafeFindClass(String rootPackage, boolean recursive, Predicate<Class> condition) {
        Collection<String> classNames = getClassNames(rootPackage, recursive);

        Collection<Class<E>> classes = new ArrayList<>();
        for (String className : classNames) {
            try {
                Class<E> clazz = (Class<E>) Class.forName(className);
                if (condition.test(clazz)) {
                    classes.add(clazz);
                }
            } catch (Throwable e) {
                logger.error(className + "被找到，但无法加载");
            }

        }
        return classes;
    }

    /**
     * 从包package中获取所有的Class
     */
    public static Collection<String> getClassNames(String rootPackage, boolean recursive) {

        // 第一个class类的集合  
        Collection<String> classes = new ArrayList<>();
        // 获取包的名字 并进行替换  
        String packageName = rootPackage;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things  
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            // 循环迭代下去  
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                // 得到协议的名称  
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上  
                if ("file".equals(protocol)) {
                    // 获取包的物理路径  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中  
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件  
                    logger.debug("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar  
                        jar = ((JarURLConnection) url.openConnection())
                                .getJarFile();
                        // 从此jar包 得到一个枚举类  
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代  
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的  
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串  
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同  
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包  
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."  
                                    packageName = name.substring(0, idx)
                                            .replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包  
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名  
                                        String className = name.substring(
                                                packageName.length() + 1, name
                                                        .length() - 6);

                                        // 添加到classes  
                                        classes.add(packageName + '.' + className);

                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        logger.error("扫描class时发生IO异常", e);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     */
    private static void findAndAddClassesInPackageByFile(String packageName,
                                                         String packagePath, final boolean recursive, Collection<String> classes) {

        File dir = new File(packagePath);

        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        //只要class文件和目录
        File[] dirfiles = dir.listFiles(CLASS_FILE_AND_DIR_FILTER);

        for (File file : dirfiles) {
            // 如果是目录 则迭代扫描  
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名  
                String className = removeClassSuffix(file.getName());
                classes.add((packageName + '.' + className));
            }
        }
    }

    private static String removeClassSuffix(String className) {
        return className.substring(0, className.length() - 6);
    }


    public static class OrCondition implements Predicate<Class> {

        private Class<?>[] checkClass;

        public OrCondition(Class<?>... checkClass) {
            this.checkClass = checkClass;
        }

        @Override
        public boolean test(Class clazz) {
            for (Class<?> cc : checkClass) {
                if (cc.isAnnotation() && (clazz.getAnnotation(cc) != null)) {
                    return true;
                } else if (cc.isAssignableFrom(clazz)) {
                    return true;
                }
            }
            return false;
        }


    }


    public static class AndCondition implements Predicate<Class> {
        private Class<?>[] checkClass;

        public AndCondition(Class<?>... checkClass) {
            this.checkClass = checkClass;
        }

        @Override
        public boolean test(Class clazz) {

            for (Class<?> cc : checkClass) {


                if (cc.isAnnotation()) {
                    if (clazz.getAnnotation(cc) == null) {
                        return false;
                    }
                } else if (!cc.isAssignableFrom(clazz)) {
                    return false;

                }
            }
            return true;
        }

    }
}
