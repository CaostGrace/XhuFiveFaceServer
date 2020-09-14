package cn.logcode.xhufiveface.result;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    SERVER_ERROR(500, "系统内部错误"),
    SERVER_IP_ERROR(501, "IP地址不在白名单内"),
    FAILED(502, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    PARAM_ERROR(405, "参数错误"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    USER_DISABLED(401, "用户被禁用"),
    SERVICE_BUSINESS_ERROR(414, "服务业务错误"),
    FORBIDDEN(403, "没有相关权限");


    public int code;
    public String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
