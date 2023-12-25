package zzzde.project.technic.trycatch;

/**
 * 自定义非法参数异常
 *
 * @author daiwl
 */
public class MyIllegalArgumentException extends MyRuntimeException {

    private static final long serialVersionUID = -6203052075893062972L;

    public MyIllegalArgumentException(){
        super();
    }

    public MyIllegalArgumentException(String message) {
        super(message);
    }

    public MyIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public MyIllegalArgumentException(ErrorCodeEnum error, String message) {
        super(error,message);
    }

  
}
