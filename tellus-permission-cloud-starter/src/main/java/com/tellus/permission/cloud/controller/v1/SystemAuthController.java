package com.tellus.permission.cloud.controller.v1;

import com.google.common.base.Strings;
import com.tellus.permission.cloud.controller.BaseController;
import com.tellus.permission.cloud.service.ISystemAuthService;
import com.tellus.permission.oauth2.TellusSecurityProperties;
import com.tellus.permission.oauth2.service.AuthorizationService;
import com.tellus.permission.oauth2.support.CustomizeUserDetails;
import com.tellus.permission.oauth2.support.UserDetailsUtils;
import com.tellus.support.Result;
import com.tellus.support.annotation.IOperationLog;
import com.tellus.support.enums.OperationTypeEnum;
import com.tellus.support.enums.RelationTypeEnum;
import com.tellus.support.enums.SignTypeEnum;
import com.tellus.support.enums.SystemErrorCodeEnum;
import com.tellus.support.exception.AccessDeniedException;
import com.tellus.support.exception.NotMatchException;
import com.tellus.support.model.vo.result.MenuVO;
import com.tellus.support.model.vo.result.UserVO;
import com.tellus.toolkit.RelationKit;
import com.tellus.toolkit.tree.Node;
import com.tellus.toolkit.tree.NodeBuilder;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证/授权管理
 *
 * @author Roy
 * @date 2020/7/16 22:06
 */
@Slf4j
@Validated
@RestController
@Api(tags = "认证/授权管理")
public class SystemAuthController extends BaseController {

    // ~ Static fields/initializers
    // ==============================================================================

    private final TellusSecurityProperties tellusSecurityProperties;
    private final ConsumerTokenServices consumerTokenServices;
    private final ISystemAuthService systemAuthService;
    private final AuthorizationService authorizationService;
    private final TokenStore tokenStore;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public SystemAuthController(TellusSecurityProperties tellusSecurityProperties,
                                ConsumerTokenServices consumerTokenServices,
                                ISystemAuthService systemAuthService,
                                AuthorizationService authorizationService,
                                TokenStore tokenStore) {
        this.tellusSecurityProperties = tellusSecurityProperties;
        this.consumerTokenServices = consumerTokenServices;
        this.systemAuthService = systemAuthService;
        this.authorizationService = authorizationService;
        this.tokenStore = tokenStore;
    }

    // ~ Main Methods
    // ==============================================================================

    @GetMapping(value = "/mine/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取当前用户的菜单")
    public Result<List<Node<MenuVO>>> menu() {
        Set<Integer> roleIds = UserDetailsUtils.obtainUserDetails()
                .getRoles()
                .stream()
                .map(CustomizeUserDetails.Role::getId)
                .collect(Collectors.toSet());

        List<MenuVO> menus = systemAuthService.getMenus(roleIds);
        return Result.success(new NodeBuilder<Integer, MenuVO>().relation(RelationKit.getRelation()).toNode(menus));
    }

    @GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("登出")
    public Result<Boolean> logout(HttpServletRequest request) {
        String authHeader = request.getHeader(tellusSecurityProperties.getTokenHeader());
        String tokenPrefix = tellusSecurityProperties.getTokenPrefix();
        if (!Strings.isNullOrEmpty(authHeader) && authHeader.startsWith(tokenPrefix)) {
            String xToken = authHeader.substring(tokenPrefix.length() + 1);
            consumerTokenServices.revokeToken(xToken);
        }
        log.info("Logout successful, {}", UserDetailsUtils.obtainUsername());
        //  记录登出日志
        systemAuthService.signSuccessAfter(SignTypeEnum.OUT);
        return Result.success("Logout successful", Boolean.TRUE);
    }

    @IOperationLog(type = OperationTypeEnum.OFFLINE, theme = "强制用户下线")
    @PostMapping(value = "/offline/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "强制用户下线", notes = "强制指定用户下线")
    @ApiImplicitParam(name = "users", value = "用户名", required = true, example = "user001, user002", dataType = "String[]")
    public Result<Boolean> offlineUsers(@Valid @NotEmpty(message = "用户名不能为空")
                                        @RequestParam(value = "usernames") Set<String> usernames) {
        String clientId = tellusSecurityProperties.getClientId();
        usernames.forEach(username -> {
            Collection<OAuth2AccessToken> existingAccessTokens =
                    this.tokenStore.findTokensByClientIdAndUserName(clientId, username);
            this.removeAccessAndRefreshToken(username, existingAccessTokens);
        });
        return Result.success();
    }

    @IOperationLog(type = OperationTypeEnum.OFFLINE, theme = "强制所有用户下线")
    @PostMapping(value = "/offline", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "强制所有用户下线", notes = "强制所有用户下线")
    public Result<Boolean> offlineAllUser() {
        String clientId = tellusSecurityProperties.getClientId();
        this.removeAccessAndRefreshToken(null, tokenStore.findTokensByClientId(clientId));
        return Result.success();
    }

    // ~ 此方法为显示登陆 swagger 文档

    @SuppressWarnings("unused")
    @PostMapping(value = "/demo -> ", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("登入, 【实际路由: /oauth/token】")
    public TokenVO signIn(SignInVO signInVO) {
        return new TokenVO();
    }

    // ~ Protected Methods
    // ==============================================================================


    // ~ Override Methods
    // ==============================================================================


    // ~ Private Methods
    // ==============================================================================

    /**
     * 删除 TOKEN
     *
     * @param username             用户名
     * @param existingAccessTokens TOKEN
     */
    private void removeAccessAndRefreshToken(String username, Collection<OAuth2AccessToken> existingAccessTokens) {
        for (OAuth2AccessToken accessToken : existingAccessTokens) {
            if (null == username) {
                username = "default";
                OAuth2Authentication auth2Authentication = this.tokenStore.readAuthentication(accessToken);
                if (null != auth2Authentication) {
                    username = auth2Authentication.getUserAuthentication().getName();
                }
            }

            log.info("Start forcing user ({}) to go offline", username);
            try {
                //  数据权限校验
                final String name = username;
                UserVO userVO = systemAuthService.findUserByUsername(username)
                        .orElseThrow(() -> new AccessDeniedException(SystemErrorCodeEnum.FORBIDDEN,
                                "Username [(" + name + ")] Not Found"));
                this.authorizationService.isSubordinate(RelationTypeEnum.USER, userVO.getId());

                if (null != accessToken.getRefreshToken()) {
                    this.tokenStore.removeAccessTokenUsingRefreshToken(accessToken.getRefreshToken());
                    this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());
                } else {
                    this.tokenStore.removeAccessToken(accessToken);
                }
                log.info("Force user ({}) to go offline successfully", username);
            } catch (AccessDeniedException e) {
                log.warn("User ({}) is not a subordinate of the current user ({})",
                        username, UserDetailsUtils.obtainUsername());
            } catch (Exception e) {
                throw new NotMatchException(e);
            }
        }
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Data
    @ApiModel(description = "登入VO")
    private static class SignInVO {

        @ApiModelProperty(value = "用户名", example = "user001")
        private String username;

        @ApiModelProperty(value = "密码", example = "123456")
        private String password;

        @ApiModelProperty(value = "授予类型", example = "bearer")
        private String grant_type;

        @ApiModelProperty(value = "作用域", example = "all")
        private String scope;

        @ApiModelProperty(value = "client_id", example = "tellus-cloud")
        private String client_id;

        @ApiModelProperty(value = "client_secret", example = "123456")
        private String client_secret;
    }

    @Data
    @ApiModel(description = "Token")
    private static class TokenVO {
        @ApiModelProperty(value = "Token", example = "2dfwse-d12d12-dfsdf2-df123d-232cdfsdfsdfsd")
        private String value;

        @ApiModelProperty(value = "过期时间", example = "2020-07-24")
        private String expiration;

        @ApiModelProperty(value = "加密类型", example = "bearer")
        private String tokenType;

        @ApiModelProperty(value = "刷新Token", example = "2dfwse-d12d12-dfsdf2-df123d-232cdfsdfsdfsd")
        private String refreshToken;

        @ApiModelProperty(value = "作用域", example = "all")
        private String scope;

        @ApiModelProperty(value = "附加信息", example = "[]")
        private String[] additionalInformation;

        @Data
        @ApiModel(description = "刷新TOKEN")
        private static class RefreshToken {
            @ApiModelProperty(value = "Token", example = "2dfwse-d12d12-dfsdf2-df123d-232cdfsdfsdfsd")
            private String value;

            @ApiModelProperty(value = "过期时间", example = "2020-07-24")
            private String expiration;
        }
    }
}
