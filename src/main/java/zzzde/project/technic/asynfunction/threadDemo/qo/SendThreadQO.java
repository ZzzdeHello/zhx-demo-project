package zzzde.project.technic.asynfunction.threadDemo.qo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 派单操作前端传入的入参
 *
 * @author zzzde
 * @version 1.0
 * @date 2023/4/18 9:45
 */
@Data
@Accessors(chain = true)
public class SendThreadQO {

    /**
     * 派单类型，见枚举Type
     */
    public Integer type;

    /**
     * 派单参数
     */
    public String paramString;

    /**
     * 派单参数
     */
    public Integer paramInt;

    @Override
    public String toString() {
        return "SendThreadQO{" +
                "type=" + type +
                ", paramString='" + paramString + '\'' +
                ", paramInt=" + paramInt +
                '}';
    }
}
