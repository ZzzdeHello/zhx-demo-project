package trycatch;

/**
 * 错误枚举类，错误编码定义
 * <p>
 * 错误级别            错误大类               错误小类            自增序列
 * 编码   含义      编码      含义          编码     含义           编码
 * 10    严重       10      应用类          10     文件类         00001
 * 20    重要       11      平台类          11     参数类         00002
 * 30    一般       12      硬件类          12     健值类         00003
 *                  13      网络类          13     权限类         00004
 *                  14      数据库类        14     格式类         00005
 *                  15      中间件类        15     认证类         00006
 *                  99      其他类          16     规则类         00007
 *                                          17     信息类         00008
 *                                          18     配置类         00009
 *                                          19     业务
 *                                          20     进程
 *                                          21     内存
 *                                          22     空间
 *                                          23     CPU
 *                                          99     其他
 *
 * @author daiwl
 */
public enum ErrorCodeEnum {
    NOT_FIND_BATCH_PARSE_ERROR("1010180001","模版订单数据配置异常"),
    BATCH_PARSE_EXEC_ERROR("1010180002", "模版订单解析时异常"),
    BATCH_TEMPLATE_CREATE_ERROR("1010180003", "订单模版生成异常"),
    BATCH_TEMPLATE_SEARCH_ERROR("1010180004", "订单模版查询异常"),
    BATCH_TEMPLATE_DB_ERROR("1010180005", "订单模版数据库更新异常"),
    BATCH_TEMPLATE_LOAD_ERROR("1010180006", "订单模版加载异常"),
    SHOPPING_CART_CACHE_NO_DATA_ERROR("10101700001", "查询不到缓存数据！"),
    URL_POST_ERROR("10101300001", "url无权限请求！"),
    USER_LOGIN_ERROR("10101500001", "系统登录异常"),
    NO_LOCATION_CUST("10101500002", "客户未定位"),
    EXCEPTION("10109900001", "服务器异常"),
    PARTITION_KEY_OR_INDEX("10101200001", "分片键或者分片索引问题"),
    NOT_NULL("20101100001", "参数不能为空"),
    INVALID_PARAM("20101100002", "无效参数"),
    USER_NAME_NULL("20101100003","用户名不能为空"),
    USER_NOT_EXIST("20101100004","用户不存在"),
    PASSWORD_ERROR("20101100005","密码不正确"),
    DUBBO_FAILURE("20101700001", "dubbo调用返回失败结果"),
    RETURN_FAILURE("20101700002", "返回结果失败"),
    NO_RESULT("30101700001", "查询无结果"),
    MULTI_RESULT("30101700002","查询返回多条结果"),
    BUS_PROCESS_EXCEPTION("10101900001", "业务处理框架异常"),
    SIMP_BUS_PROCESS_EXCEPTION("10101900002", "业务处理异常"),
    RULE_VALIDATE_FAILED("20101600001", "规则校验失败"),
    PRE_VALIDATE_FAILED("20101600002", "预校验失败"),
    SYNC_CRM_FAILED("20101900001", "同步CRM失败"),
    RETURN_FILE_FAILED("20101900002", "返档失败"),
    ZICHASNG_CRM_FAILED("30101900001", "资产归并CRM失败"),
    BATCH_FAILURE("20101000001", "批量开始录入失败"),
    QUERY_OUT("30101000001", "批量开始录入失败"),
    JEDIS_ERROR("10151800001", "jedis异常"),
    CACHE_SERVICE_ERROR("20991900001", "jedis异常"),
    DB_SERVICE_ERROR("20991900002", "DB异常"),
    SMS_ERROR("2010170003","短信发送失败"),
    PASSWORD_EXPIRE("30111500001","密码即将失效"),
    PASSWORD_INVALID("30111500002","密码失效"),
    PASSWORD_OR_USERNAME_ERROR("30111500003","用戶名或密码错误"),
    USER_LOCK("20111500001","用户锁定"),
    IDCARD_VERIFY_FAILED("2010150001","身份证读卡校验失败"),
    IDCARD_LOGIN_FAILED("2010150001","身份证登陆校验失败"),
    XML_READER("30111500003","解析XML失败"),
    OGNL_EXCEPTION("30111500004", "OGNL表达式异常"),
    IO_EXCEPTION("30111500005", "IO读写异常"),
    SEND_MQ_EXCEPTION("30111500006", "发送消息异常"),
    QUICK_ORDER_CREATE_EXCEPTION("10101900003", "交易单生产异常"),
    BATCH_ORDER_CREATE_EXCEPTION("10101900004", "批量订单生成异常"),
    BATCH_ORDER_SHENHE("10101900010", "超过阀值需要审核编码"),
    BATCH_ORDER_SHENHEWD("10101900011", "未到阀值需要审核"),
    RULE_OLD_VERIFY_FAILED("10101900011", "2.0规则校验失败"),
    RULE_ALERT("30101900002", "提示规则警告"),
    RULE_SET_FAILED("10101900012", "设置规则执行失败"),
    RULE_VERIFY_FAILED("10101900013", "验证规则验证失败"),
    RATE_LIMIT("101015190001", "系统过载保护"),
    RESUBMIT_LIMIT("101015190002", "禁止重复提交"),


    ;



    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误类型
     */
    private String errorType;

    ErrorCodeEnum(String errorCode, String errorType) {
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorType() {
        return this.errorType;
    }
}
