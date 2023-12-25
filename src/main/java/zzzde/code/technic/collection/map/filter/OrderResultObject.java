package zzzde.code.technic.collection.map.filter;

import java.io.Serializable;

/**
 * 对外暴露服务的返回对象
 *
 * @param <T> 泛型参数，代表返回对象里所包含的数据对象的类型
 * @author daiwl
 */
public class OrderResultObject<T> implements Serializable {
    private static final long serialVersionUID = -7026043870355150977L;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应码
     */
    private String resultCode;

    /**
     * 响应信息
     */
    private String resultMsg;

    /**
     * 响应数据对象
     */
    private T resultObject;


    private String orderNum;

    public static <T> OrderResultObject<T> buildSuccessResponse() {
        return new OrderResultObject<T>(true, "success");
    }

    public static <T> OrderResultObject<T> buildSuccessResponse(String resultCode, String resultMsg) {
        return new OrderResultObject<T>(resultCode, resultMsg);
    }

//    public static <T> OrderResultObject<T> buildSuccessResponse(String resultCode) {
//        return new OrderResultObject<T>(resultCode, "success");
//    }

    public static <T> OrderResultObject<T> buildSuccessResponse(T data) {
        return new OrderResultObject<T>(true, "success", data);
    }

    public static <T> OrderResultObject<T> buildFailureResponse(String message) {
        return new OrderResultObject<T>(false, message);
    }

    public static <T> OrderResultObject<T> buildFailureResponse(String resultCode, String message) {
        return new OrderResultObject<T>(false, resultCode, message, null);
    }

    public OrderResultObject() {
    }

    public OrderResultObject(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    public OrderResultObject(String resultCode, String resultMsg, T resultObject) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultObject = resultObject;
    }

    public OrderResultObject(boolean success, String resultMsg, T data) {
        this.success = success;
        this.resultMsg = resultMsg;
        this.resultObject = data;
    }

    public OrderResultObject(boolean success, String resultCode, String resultMsg, T data) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultObject = data;
    }

    public OrderResultObject(boolean success, String resultMsg) {
        this.success = success;
        this.resultMsg = resultMsg;
    }

    public OrderResultObject(String resultCode, String resultMsg, String orderNum, T resultObject) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.orderNum = orderNum;
        this.resultObject = resultObject;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultObject() {
        return resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
