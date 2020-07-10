package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IRetrieveService;
import com.tellus.permission.cloud.support.ISaveService;
import com.tellus.support.model.vo.create.CreateLoginLogVO;
import com.tellus.support.model.vo.result.LoginLogVO;
import com.tellus.support.model.vo.retrieve.RetrieveLoginLogVO;

/**
 * 登录日志服务类
 *
 * @author Roy
 * @date 2020/7/10 17:04
 */
public interface ILoginLogService extends IRetrieveService<LoginLogVO, RetrieveLoginLogVO>
        , ISaveService<CreateLoginLogVO> {

}
