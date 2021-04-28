package com.tellus.permission.cloud.support;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import com.tellus.permission.oauth2.support.UserDetailsUtils;
import com.tellus.toolkit.CharsKit;
import com.tellus.toolkit.ResourceKit;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ClassUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FieldCacheUtils {

    private static final Cache<String, Set<CustomizeUserDetails.Field>> FIELD_CACHE = CacheBuilder
            .newBuilder()
            .maximumSize(500)
            .expireAfterAccess(30, TimeUnit.SECONDS)
            .build();

    @SneakyThrows
    public static Set<CustomizeUserDetails.Field> getFields() {
        if (!UserDetailsUtils.obtainAuthentication().isAuthenticated()) {
            return Sets.newHashSet();
        }

        Object principal = UserDetailsUtils.obtainAuthentication().getPrincipal();
        if (!ClassUtils.isAssignable(principal.getClass(), CustomizeUserDetails.class)) {
            return Sets.newHashSet();
        }

        String requestUrl = ResourceKit.builderValue();
        String id = UserDetailsUtils.obtainUsername() + CharsKit.UNDERLINE + requestUrl;

        return FIELD_CACHE.get(id, UserDetailsUtils::obtainFieldOfRequest);
    }
}
