package com.tellus.permission.cloud.configuration;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;
import com.tellus.config.gson.CustomizeEnumTypeAdapterFactory;
import com.tellus.config.gson.LocalDateJsonConverter;
import com.tellus.config.gson.LocalDateTimeJsonConverter;
import com.tellus.permission.TellusPermissionProperties;
import com.tellus.permission.cloud.support.gson.ResourcesDeserializationExclusionStrategy;
import com.tellus.permission.cloud.support.gson.ResourcesSerializationExclusionStrategy;
import com.tellus.permission.cloud.support.gson.ValuesJsonSerializer;
import com.tellus.permission.oauth2.TellusSecurityProperties;
import com.tellus.toolkit.DateFormatConstants;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class GsonConfiguration {

    // ~ Static fields/initializers
    // ========================================================================================================

    private static final ClassLoader CLASS_LOADER = GsonConfiguration.class.getClassLoader();
    private final TellusSecurityProperties securityProperties;
    private final TellusPermissionProperties tellusPermissionProperties;
    private final WebMvcProperties webMvcProperties;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public GsonConfiguration(TellusSecurityProperties securityProperties,
                             TellusPermissionProperties tellusPermissionProperties,
                             WebMvcProperties webMvcProperties) {
        this.securityProperties = securityProperties;
        this.tellusPermissionProperties = tellusPermissionProperties;

        this.webMvcProperties = webMvcProperties;
    }

    // ~ Bean Methods
    // ========================================================================================================

    @Bean
    public ExclusionStrategy resourcesDeserializationExclusionStrategy() {
        return new ResourcesDeserializationExclusionStrategy(securityProperties.getIgnoredUrls(), webMvcProperties.getServlet().getServletPrefix());
    }

    @Bean
    public ExclusionStrategy resourcesSerializationExclusionStrategy() {
        return new ResourcesSerializationExclusionStrategy(securityProperties.getIgnoredUrls(), webMvcProperties.getServlet().getServletPrefix());
    }

    @SuppressWarnings("UnstableApiUsage")
    @Bean
    public Set<ClassPath.ClassInfo> getClassInfo() {
        ClassPath classPath;
        try {
            classPath = ClassPath.from(CLASS_LOADER);
            ImmutableSet<ClassPath.ClassInfo> classInfos = ImmutableSet.copyOf(
                    tellusPermissionProperties.getModelPackages()
                            .stream()
                            .map(classPath::getTopLevelClassesRecursive)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toSet())
            );

            return classInfos
                    .stream()
                    .filter(classInfo -> classInfo.getSimpleName().endsWith("VO")
                            || classInfo.getSimpleName().endsWith("PageWrapper"))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ~ Private Methods
    // ========================================================================================================

    @SuppressWarnings("UnstableApiUsage")
    private GsonBuilder gsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .disableHtmlEscaping()
                .registerTypeHierarchyAdapter(LocalDate.class, new LocalDateJsonConverter())
                .registerTypeHierarchyAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter())
                .registerTypeAdapterFactory(new CustomizeEnumTypeAdapterFactory())
                .addDeserializationExclusionStrategy(resourcesDeserializationExclusionStrategy())
                .addSerializationExclusionStrategy(resourcesSerializationExclusionStrategy())
                .setDateFormat(DateFormatConstants.YYYYMMDDHHMMSS);
        Set<ClassPath.ClassInfo> classInfos = getClassInfo()
                .stream()
                .filter(classInfo -> !classInfo.getSimpleName().equals("MenuVO"))
                .collect(Collectors.toSet());
        for (ClassPath.ClassInfo classInfo : classInfos) {
            gsonBuilder.registerTypeAdapter(classInfo.load(), new ValuesJsonSerializer<>(gsonBuilder.create()));
        }
        return gsonBuilder;
    }

}
