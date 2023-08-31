package asynFunction.threadDemo.localThread;

import asynFunction.threadDemo.SendAbstractThread;
import asynFunction.threadDemo.qo.SendThreadQO;
import function.supplier.Product;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/18 9:21
 */
public class FileParseSenderThread extends SendAbstractThread {

    // 调用父类构造器，且该QO参数必须存在
    public FileParseSenderThread(SendThreadQO qo) {
        super(qo);
    }

    @Override
    public void run() {
        System.out.println("--解析文件线程:开始执行--");
        System.out.println(qo.toString());
        System.out.println("--解析文件线程:结束执行--");
    }
}
