package com.tellus.permission.oauth2.service;

import com.tellus.permission.oauth2.TellusSecurityProperties;
import com.tellus.permission.oauth2.support.CustomizeClientDetails;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * 实现 OAuth2 获取客户端信息接口
 *
 * @author Roy
 * @date 2020/7/6 15:07
 */
@Service("clientDetailsService")
public class IClientDetailsServiceImpl implements ClientDetailsService {

    private final TellusSecurityProperties tellusSecurityProperties;

    @Autowired
    public IClientDetailsServiceImpl(TellusSecurityProperties tellusSecurityProperties) {
        this.tellusSecurityProperties = tellusSecurityProperties;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return new CustomizeClientDetails(tellusSecurityProperties);
    }

}
