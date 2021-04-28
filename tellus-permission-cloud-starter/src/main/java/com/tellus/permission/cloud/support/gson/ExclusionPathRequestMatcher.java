package com.tellus.permission.cloud.support.gson;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExclusionPathRequestMatcher implements RequestMatcher {

    private final OrRequestMatcher ignoreOrRequestMatcher;

    public ExclusionPathRequestMatcher(String ignoreUrls, String servletPath) {
        String oauthTokenUrl = servletPath.contains("/oauth/token") ? servletPath : "/oauth/token";
        ignoreUrls = ignoreUrls.concat(".").concat(oauthTokenUrl);
        List<RequestMatcher> ignoreOrAntMatchers = Arrays.stream(ignoreUrls.split(","))
                .map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
        this.ignoreOrRequestMatcher = new OrRequestMatcher(ignoreOrAntMatchers);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return !ignoreOrRequestMatcher.matches(request);
    }
}
