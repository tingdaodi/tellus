package com.tellus.permission.cloud.service;

import com.tellus.permission.cloud.support.IBasicService;
import com.tellus.support.model.vo.create.CreateSysPropertiesVO;
import com.tellus.support.model.vo.result.SysPropertiesVO;
import com.tellus.support.model.vo.retrieve.RetrieveSysPropertiesVO;
import com.tellus.support.model.vo.update.UpdateSysPropertiesVO;

import java.util.Optional;

/**
 * 系统常量配置服务类
 *
 * @author Roy
 * @date 2020/7/13 12:26
 */
public interface ISysPropertiesService extends IBasicService<SysPropertiesVO,
        CreateSysPropertiesVO, RetrieveSysPropertiesVO, UpdateSysPropertiesVO> {

    /**
     * 根据 KEY 查询配置信息
     *
     * @param key KEY
     * @return Optional<SysPropertiesVO>
     */
    Optional<SysPropertiesVO> findByKey(String key);

}
