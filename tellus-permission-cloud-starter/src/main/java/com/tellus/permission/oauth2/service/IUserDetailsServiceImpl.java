package com.tellus.permission.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现 Security 获取用户数据接口
 *
 * @author Roy
 * @date 2020/7/9 12:14
 */
@Service(BeanIds.USER_DETAILS_SERVICE)
public class IUserDetailsServiceImpl implements UserDetailsService {

    private final AuthorizationService authorizationService;

    @Autowired
    public IUserDetailsServiceImpl(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return authorizationService.loadUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }
}
