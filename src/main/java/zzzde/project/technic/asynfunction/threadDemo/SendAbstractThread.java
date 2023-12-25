package zzzde.project.technic.asynfunction.threadDemo;

import zzzde.project.technic.asynfunction.threadDemo.qo.SendThreadQO;
import lombok.Data;

/**
 * 定义下发线程基础属性
 */
@Data
public abstract class SendAbstractThread implements Runnable {

    // 线程特定字段
    protected static String name;
    // 线程特定字段
    protected static Integer age;

    protected SendThreadQO qo;

    public SendAbstractThread(SendThreadQO qo) {
        this.qo = qo;
    }
}
