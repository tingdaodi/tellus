DROP TABLE T_ROLE;;/*SkipError*/
CREATE TABLE T_ROLE
(
    ID         INT          NOT NULL COMMENT '主键',
    NAME       VARCHAR(128) NOT NULL COMMENT '角色名称',
    ROLE       VARCHAR(128) NOT NULL COMMENT '角色标识',
    REMARK     VARCHAR(512) COMMENT '备注',
    ENABLED    INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-启用',
    CREATOR    VARCHAR(128) NOT NULL COMMENT '创建人',
    CREATED_AT DATETIME     NOT NULL COMMENT '创建时间',
    UPDATER    VARCHAR(128) COMMENT '更新人',
    UPDATED_AT DATETIME COMMENT '更新时间',
    PRIMARY KEY (ID)
) COMMENT = '角色表 角色信息';;
ALTER TABLE T_ROLE
    COMMENT '角色表';;

DROP TABLE T_RESOURCE;;/*SkipError*/
CREATE TABLE T_RESOURCE
(
    ID         INT          NOT NULL COMMENT '主键 主键',
    NAME       VARCHAR(128) NOT NULL COMMENT '资源名称 资源名称',
    VALUE      VARCHAR(512) NOT NULL COMMENT '资源值 资源值',
    ENABLED    INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-启用',
    REMARK     VARCHAR(512) COMMENT '备注 备注',
    CREATOR    VARCHAR(128) NOT NULL COMMENT '创建人 创建人',
    CREATED_AT DATETIME     NOT NULL COMMENT '创建时间 创建时间',
    UPDATER    VARCHAR(128) COMMENT '更新人 更新人',
    UPDATED_AT DATETIME COMMENT '更新时间 更新时间',
    PRIMARY KEY (ID)
) COMMENT = '资源表 资源信息';;
ALTER TABLE T_RESOURCE
    COMMENT '资源表';;

DROP TABLE T_USER;;/*SkipError*/
CREATE TABLE T_USER
(
    ID              INT           NOT NULL COMMENT '主键 主键',
    USERNAME        VARCHAR(32)   NOT NULL COMMENT '用户名 用户名',
    PASSWORD        VARCHAR(1024) NOT NULL COMMENT '密码 密码密文',
    NICKNAME        VARCHAR(128) COMMENT '昵称 昵称',
    GENDER          INT(2) DEFAULT 1 COMMENT '性别 1-男，2-女；默认：1',
    USER_TYPE       INT(2) DEFAULT 1 COMMENT '用户类型 1-内部用户，2-外部用户；默认：1',
    STATUS          INT(2) DEFAULT 1 COMMENT '状态 1-正常，2-禁用；默认：1',
    EMAIL           VARCHAR(1024) COMMENT '邮箱 邮箱地址',
    PHONE           VARCHAR(1024) COMMENT '联系电话 联系电话',
    OFFICE_PHONE    VARCHAR(1024) COMMENT '办公电话 办公电话',
    BIRTHDAY        DATETIME COMMENT '出生日期 出生日期',
    AVATAR          VARCHAR(3072) COMMENT '头像 头像',
    LANGUAGE        INT(2) COMMENT '使用语言 使用语言',
    IP_ADDRESS      VARCHAR(128) COMMENT '注册IP地址 注册地址',
    LAST_LOGIN_TIME DATETIME COMMENT '最后登录时间 最后登录时间',
    LAST_LOGIN_IP   VARCHAR(128) COMMENT '最后登录ID地址 最后登录IP地址',
    REMARKS         VARCHAR(32) COMMENT '备注 备注',
    CREATOR         VARCHAR(32)   NOT NULL COMMENT '创建人 创建人',
    CREATED_AT      DATETIME      NOT NULL COMMENT '创建时间 创建时间',
    UPDATER         VARCHAR(32) COMMENT '更新人 更新人',
    UPDATED_AT      DATETIME COMMENT '更新时间 更新时间',
    DELETED         INT(1) DEFAULT 0 COMMENT '逻辑删除 0-未删除，1-已删除；默认：0',
    PRIMARY KEY (ID)
) COMMENT = '用户表 用户信息';;
ALTER TABLE T_USER
    COMMENT '用户表';;

DROP TABLE T_GROUP;;/*SkipError*/
CREATE TABLE T_GROUP
(
    ID         INT         NOT NULL COMMENT '主键 主键',
    NAME       VARCHAR(32) NOT NULL COMMENT '组织名称 组织名称',
    CODE       VARCHAR(32) NOT NULL COMMENT '组织编号 组织编号',
    ENABLED    INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-启用',
    REMARK     VARCHAR(32) COMMENT '备注 备注',
    CREATOR    VARCHAR(32) NOT NULL COMMENT '创建人 创建人',
    CREATED_AT DATETIME    NOT NULL COMMENT '创建时间 创建时间',
    UPDATER    VARCHAR(32) COMMENT '更新人 更新人',
    UPDATED_AT DATETIME COMMENT '更新时间 更新时间',
    PRIMARY KEY (ID)
) COMMENT = '组织表 组织信息';;
ALTER TABLE T_GROUP
    COMMENT '组织表';;

DROP TABLE T_FIELD;;/*SkipError*/
CREATE TABLE T_FIELD
(
    ID          INT         NOT NULL COMMENT '主键 主键',
    RESOURCE_ID INT         NOT NULL COMMENT '所属资源ID 所属资源ID',
    METHOD      INT(2)      NOT NULL COMMENT '出/入参 1-入参，2-回参',
    LABEL       VARCHAR(32) NOT NULL COMMENT '参数标签 参数标签',
    NAME        VARCHAR(32) NOT NULL COMMENT '参数名称 参数名称',
    TYPE        VARCHAR(32) NOT NULL COMMENT '参数类型 参数类型',
    ENABLED     INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-启用',
    REMARK      VARCHAR(32) COMMENT '备注 备注',
    CREATOR     VARCHAR(32) NOT NULL COMMENT '创建人 创建人',
    CREATED_AT  DATETIME    NOT NULL COMMENT '创建时间 创建时间',
    UPDATER     VARCHAR(32) COMMENT '更新人 更新人',
    UPDATED_AT  DATETIME COMMENT '更新时间 更新时间',
    PRIMARY KEY (ID)
) COMMENT = '字段表 字段信息';;
ALTER TABLE T_FIELD
    COMMENT '字段表';;

DROP TABLE T_MENU;;/*SkipError*/
CREATE TABLE T_MENU
(
    ID         INT          NOT NULL COMMENT '主键 主键',
    NAME       VARCHAR(128) NOT NULL COMMENT '菜单名称 菜单名称',
    VALUE      VARCHAR(512) NOT NULL COMMENT '菜单的值 菜单的值',
    ENABLED    INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-已启用；默认：1',
    REMARK     VARCHAR(512) COMMENT '备注 备注',
    SORT       INT    DEFAULT 1 COMMENT '排序 排序',
    CREATOR    VARCHAR(128) NOT NULL COMMENT '创建人 创建人',
    CREATED_AT DATETIME     NOT NULL COMMENT '创建时间 创建时间',
    UPDATER    VARCHAR(128) COMMENT '更新人 更新人',
    UPDATED_AT DATETIME COMMENT '更新时间 更新时间',
    PRIMARY KEY (ID)
) COMMENT = '菜单表 菜单表';;
ALTER TABLE T_MENU
    COMMENT '菜单表';;

DROP TABLE T_RELATION;;/*SkipError*/
CREATE TABLE T_RELATION
(
    TYPE       INT(2) NOT NULL COMMENT '关系类型 1-用户，2-组织，3-角色，4-菜单',
    ANCESTOR   INT    NOT NULL COMMENT '祖先，上级ID 祖先ID',
    DESCENDANT INT    NOT NULL COMMENT '后代，下级ID 后代ID',
    DISTANCE   INT    NOT NULL COMMENT '隔代，祖先与后代的距离 祖先与后代的隔代距离',
    PRIMARY KEY (TYPE, ANCESTOR, DESCENDANT, DISTANCE)
) COMMENT = '层级关系表 层级关系';;
ALTER TABLE T_RELATION
    COMMENT '层级关系表';;

DROP TABLE T_PLATFORM;;/*SkipError*/
CREATE TABLE T_PLATFORM
(
    ID         INT          NOT NULL COMMENT '主键 主键',
    NAME       VARCHAR(128) NOT NULL COMMENT '平台名称 平台名称',
    CODE       VARCHAR(128) NOT NULL COMMENT '平台编号 平台编号',
    ENABLED    INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-已启用',
    CREATOR    VARCHAR(128) NOT NULL COMMENT '创建人 创建人',
    CREATED_AT DATETIME     NOT NULL COMMENT '创建时间 创建时间',
    UPDATER    VARCHAR(128) COMMENT '更新人 更新人',
    UPDATED_AT DATETIME COMMENT '更新时间 更新时间',
    PRIMARY KEY (ID)
) COMMENT = '平台信息表 系统支持多平台接入';;
ALTER TABLE T_PLATFORM
    COMMENT '平台信息表';;

DROP TABLE T_USER_PLATFORM;;/*SkipError*/
CREATE TABLE T_USER_PLATFORM
(
    USER_ID     INT NOT NULL COMMENT '用户ID 用户ID',
    PLATFORM_ID INT NOT NULL COMMENT '平台ID 平台ID',
    PRIMARY KEY (USER_ID, PLATFORM_ID)
) COMMENT = '用户平台表 用户与平台关系';;
ALTER TABLE T_PLATFORM
    COMMENT '用户平台表';;

DROP TABLE T_SYS_PROPERTIES;;/*SkipError*/
CREATE TABLE T_SYS_PROPERTIES
(
    ID         INT           NOT NULL COMMENT '主键 主键',
    `KEY`      VARCHAR(128)  NOT NULL COMMENT '系统配置KEY 系统配置KEY',
    VALUE      VARCHAR(1024) NOT NULL COMMENT '系统配置值 系统配置值',
    ENABLED    INT(1) DEFAULT 1 COMMENT '是否启用 0-未启用，1-已启用',
    REMARK     VARCHAR(512) COMMENT '备注 备注',
    CREATOR    VARCHAR(128)  NOT NULL COMMENT '创建人 创建人',
    CREATED_AT DATETIME      NOT NULL COMMENT '创建时间 创建时间',
    UPDATER    VARCHAR(128) COMMENT '更新人 更新人',
    UPDATED_AT DATETIME COMMENT '更新时间 更新时间',
    PRIMARY KEY (ID)
) COMMENT = '系统配置表 系统运行依赖配置';;
ALTER TABLE T_SYS_PROPERTIES
    COMMENT '系统配置表';;

DROP TABLE T_LOGIN_LOG;;/*SkipError*/
CREATE TABLE T_LOGIN_LOG
(
    ID          INT              NOT NULL COMMENT '主键 主键',
    USERNAME    VARCHAR(128)     NOT NULL COMMENT '用户名 用户名',
    LOGIN_TIME  VARCHAR(512)     NOT NULL COMMENT '登录时间 登录时间',
    CLIENT_IP   VARCHAR(512)     NOT NULL COMMENT '客户端IP',
    CLIENT_HOST VARCHAR(512)     NOT NULL COMMENT '客户端HOST',
    CLIENT_MAC  VARCHAR(512) COMMENT '客户端MAC 客户端MAC',
    DEVICE      VARCHAR(512) COMMENT '登录设备 登录设备',
    USER_AGENT  TEXT COMMENT '代理信息 代理信息(浏览器头信息)',
    REFERER     VARCHAR(512) COMMENT '来源地址 来源地址',
    SUCCESSFUL  INT(1) DEFAULT 1 NOT NULL COMMENT '是否成功 是否成功',
    PRIMARY KEY (ID)
) COMMENT = '登录日志表 记录登录日志';;
ALTER TABLE T_LOGIN_LOG
    COMMENT '登录日志表';;

DROP TABLE T_OPERATION_LOG;;/*SkipError*/
CREATE TABLE T_OPERATION_LOG
(
    ID                   INT          NOT NULL COMMENT '主键 主键',
    THEME                VARCHAR(512) NOT NULL COMMENT '操作主题 操作主题',
    OPERATOR             VARCHAR(128) NOT NULL COMMENT '操作人 操作人',
    OPERATE_TYPE         INT(2)       NOT NULL COMMENT '操作类型 1-查询，2-更新，3-新建，4-删除，5-逻辑删除',
    OPERATION_BEGIN_TIME DATETIME     NOT NULL COMMENT '操作开始时间 操作开始时间',
    OPERATION_END_TIME   DATETIME COMMENT '操作结束时间 操作结束时间',
    INCOMING_PARAMS      TEXT COMMENT '传入参数 传入参数',
    RESULT_PARAMS        TEXT COMMENT '返回参数 返回参数',
    SUCCESSFUL           INT(1) DEFAULT 1 COMMENT '是否成功 0-失败，1-成功',
    EXCEPTION            TEXT COMMENT '异常信息 异常信息',
    CLIENT_IP            VARCHAR(512) COMMENT '客户端IP 客户端IP',
    REQUEST_HOST         VARCHAR(512) COMMENT '请求HOST 请求HOST',
    REQUEST_URL          VARCHAR(512) COMMENT '请求URL 请求URL',
    REQUEST_METHOD       INT(2) COMMENT '请求方式 :1-GET,2-HEAD,3-POST,4-PUT,5-PATCH,6-DELETE,7-OPTIONS,8-TRACE',
    REMARK               VARCHAR(512) COMMENT '备注 备注',
    PRIMARY KEY (ID)
) COMMENT = '操作日志表 记录操作信息';;
ALTER TABLE T_OPERATION_LOG
    COMMENT '操作日志表';;

DROP TABLE T_GROUP_USER;;/*SkipError*/
CREATE TABLE T_GROUP_USER
(
    GROUP_ID INT NOT NULL COMMENT '组织ID 组织ID',
    USER_ID  INT NOT NULL COMMENT '用户ID 用户ID',
    PRIMARY KEY (GROUP_ID, USER_ID)
) COMMENT = '组织用户表 组织与用户关系信息';;
ALTER TABLE T_GROUP_USER
    COMMENT '组织用户表';;

DROP TABLE T_GROUP_ROLE;;/*SkipError*/
CREATE TABLE T_GROUP_ROLE
(
    GROUP_ID INT COMMENT '组织ID 组织ID',
    ROLE_ID  INT COMMENT '角色ID 角色ID'
) COMMENT = '组织角色表 组织与角色关系';;
ALTER TABLE T_GROUP_ROLE
    COMMENT '组织角色表';;

DROP TABLE T_USER_ROLE;;/*SkipError*/
CREATE TABLE T_USER_ROLE
(
    USER_ID INT NOT NULL COMMENT '用户ID 用户ID',
    ROLE_ID INT NOT NULL COMMENT '角色ID 角色ID',
    PRIMARY KEY (USER_ID, ROLE_ID)
) COMMENT = '用户角色表 用户与角色信息';;
ALTER TABLE T_USER_ROLE
    COMMENT '用户角色表';;

DROP TABLE T_ROLE_RESOURCE;;/*SkipError*/
CREATE TABLE T_ROLE_RESOURCE
(
    ROLE_ID     INT NOT NULL COMMENT '角色ID 角色ID',
    RESOURCE_ID INT NOT NULL COMMENT '资源ID 资源ID',
    PRIMARY KEY (ROLE_ID, RESOURCE_ID)
) COMMENT = '角色资源表 角色与资源关系';;
ALTER TABLE T_ROLE_RESOURCE
    COMMENT '角色资源表';;

DROP TABLE T_ROLE_FIELD;;/*SkipError*/
CREATE TABLE T_ROLE_FIELD
(
    ROLE_ID      INT    NOT NULL COMMENT '角色ID 角色ID',
    FIELD_ID     INT    NOT NULL COMMENT '字段ID 字段ID',
    DISPLAY_MODE INT(2) NOT NULL DEFAULT 1 COMMENT '显示模式 默认：1；1-全显示，2-半显示，3-隐藏',
    PRIMARY KEY (ROLE_ID, FIELD_ID)
) COMMENT = '角色字段表 角色与字段关系';;
ALTER TABLE T_ROLE_FIELD
    COMMENT '角色字段表';;

DROP TABLE T_ROLE_MENU;;/*SkipError*/
CREATE TABLE T_ROLE_MENU
(
    ROLE_ID INT NOT NULL COMMENT '角色ID 角色ID',
    MENU_ID INT NOT NULL COMMENT '菜单ID 菜单ID',
    PRIMARY KEY (ROLE_ID, MENU_ID)
) COMMENT = '角色菜单表 角色与菜单关系';;
ALTER TABLE T_ROLE_MENU
    COMMENT '角色菜单表';;
