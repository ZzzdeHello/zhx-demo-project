package zzzde.project.technic.trycatch;

import org.springframework.util.Assert;


/**
 * 断言工具类
 *
 * @author daiwl
 */
public class AssertUtil extends Assert {

    /**
     * 表达式为false抛出异常
     *
     * @param expression 异常表达式
     * @param message    异常信息
     */
    public static void isTrue(boolean expression, Object message) {
        if (!expression) {
            throw new MyIllegalArgumentException(ErrorCodeEnum.INVALID_PARAM,message.toString());
        }
    }

//    /**
//     * 统一判空处理
//     *
//     * @param expression 判空表达式
//     * @param message    异常信息
//     */
//    @Deprecated
//    public static void notNull(boolean expression, Object message) {
//        if (!expression) {
//            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, StringUtil.valueOf(message));
//        }
//    }
//
//    /**
//     * 统一判空处理
//     *
//     * @param expression 判空表达式
//     * @param message    异常信息
//     */
//    public static void check(boolean expression, Object message) {
//        if (!expression) {
//            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, StringUtil.valueOf(message));
//        }
//    }
//
//    /**
//     * 对象为空抛出异常
//     *
//     * @param param   参数
//     * @param message 异常信息
//     */
//    public static void notNull(Object param, String message) {
//        if (Objects.isNull(param)) {
//            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, StringUtil.valueOf(message));
//        }
//    }

//    /**
//     * 字符串为空抛出异常
//     *
//     * @param param   参数
//     * @param message 异常信息
//     */
//    public static void notEmpty(String param, String message) {
//        if (StringUtil.isBlank(param)) {
//            throw new MyIllegalArgumentException(ErrorCodeEnum.NOT_NULL, message);
//        }
//    }

//    /**
//     * 集合为空抛出异常
//     *
//     * @param collection 集合
//     * @param message    异常信息
//     */
//    public static void notEmpty(Collection<?> collection, String message) {
//        check(CollectionUtils.isNotEmpty(collection), message);
//    }
//


}
