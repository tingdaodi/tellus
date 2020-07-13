package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicService;
import com.tellus.support.model.vo.create.CreateUserVO;
import com.tellus.support.model.vo.result.UserVO;
import com.tellus.support.model.vo.retrieve.RetrieveUserVO;
import com.tellus.support.model.vo.update.UpdateUserVO;

import java.util.Optional;

/**
 * 系统用户服务类
 *
 * @author Roy
 * @date 2020/7/13 12:28
 */
public interface ISystemUserService extends IBasicService<UserVO,
        CreateUserVO, RetrieveUserVO, UpdateUserVO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return Optional<UserVO>
     */
    Optional<UserVO> findUserByUsername(String username);

}
