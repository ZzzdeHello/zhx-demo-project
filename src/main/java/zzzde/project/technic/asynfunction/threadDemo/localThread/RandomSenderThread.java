package zzzde.project.technic.asynfunction.threadDemo.localThread;

import zzzde.project.technic.asynfunction.threadDemo.SendAbstractThread;
import zzzde.project.technic.asynfunction.threadDemo.qo.SendThreadQO;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/18 9:21
 */
public class RandomSenderThread extends SendAbstractThread {


    public RandomSenderThread(SendThreadQO qo) {
        super(qo);
    }

    @Override
    public void run() {
        System.out.println("--随机解析线程:开始执行--");
        System.out.println(qo.toString());
        System.out.println("--随机解析线程:结束执行--");
    }
}
