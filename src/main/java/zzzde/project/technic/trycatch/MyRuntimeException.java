package zzzde.project.technic.trycatch;

/**
 * 自定义运行时异常，支持传入错误枚举。
 *
 * @author daiwl
 */
public class MyRuntimeException extends RuntimeException {

    private ErrorCodeEnum error;

    public MyRuntimeException() {
        super();
    }

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRuntimeException(Throwable cause) {
        super(cause);
    }

    public MyRuntimeException(ErrorCodeEnum error, String message) {
        super(message);
        this.error = error;
    }

    public MyRuntimeException(ErrorCodeEnum error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public ErrorCodeEnum getError() {
        return error;
    }

    public void setError(ErrorCodeEnum error) {
        this.error = error;
    }

    @Override
    public String toString() {
        String result;
        if (this.error != null) {
            result = "错误码:" + error.getErrorCode() + ";错误信息:" + getMessage();
        } else {
            result = super.toString();
        }
        return result;
    }
}
