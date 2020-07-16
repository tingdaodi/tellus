package com.tellus.permission.cloud.controller.v1;

import com.tellus.permission.cloud.controller.AbstractRetrieveController;
import com.tellus.permission.cloud.service.IOperationLogService;
import com.tellus.support.OperationLog;
import com.tellus.support.model.vo.retrieve.RetrieveOperationLogVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志管理
 *
 * @author Roy
 * @date 2020/7/16 20:36
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/operation/logs")
@Api(tags = "操作日志管理")
public class OperationLogController extends AbstractRetrieveController<OperationLog, RetrieveOperationLogVO> {

    // ~ Static fields/initializers
    // ==============================================================================

    private final IOperationLogService operationLogService;

    // ~ Constructors
    // ==============================================================================

    @Autowired
    public OperationLogController(IOperationLogService operationLogService) {
        super(operationLogService);
        this.operationLogService = operationLogService;
    }

    // ~ Main Methods
    // ==============================================================================


    // ~ Protected Methods
    // ==============================================================================


    // ~ Override Methods
    // ==============================================================================


    // ~ Private Methods
    // ==============================================================================

}
