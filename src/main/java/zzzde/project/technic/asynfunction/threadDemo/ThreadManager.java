package zzzde.project.technic.asynfunction.threadDemo;

import zzzde.project.technic.asynfunction.threadDemo.localThread.FileParseSenderThread;
import zzzde.project.technic.asynfunction.threadDemo.localThread.RandomSenderThread;
import zzzde.project.technic.asynfunction.threadDemo.qo.FileSenderQO;
import zzzde.project.technic.asynfunction.threadDemo.qo.SendThreadQO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.*;

/**
 * 线程分发执行逻辑
 *
 * @author zzzde
 * @version 1.0
 * @date 2023/4/18 9:26
 */
public class ThreadManager {

    private final static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,
            200,
            60L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable run) {
                    return new Thread(run, "xxl-job, admin JobTriggerPoolHelper-fastTriggerPool-" + run.hashCode());
                }
            });


    public static void main(String[] args) {
        FileSenderQO qo = new FileSenderQO();
        qo.setType(1).setParamInt(1000).setParamString("paramsStr");
        // 执行分发
        SendAbstractThread thread = getThread(qo);

        // 异步执行
        // CompletableFuture.runAsync(zzzde.project.technic.thread);
        // 线程池执行
        pool.execute(thread);

        try {
            // 等待子线程执行完成
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static SendAbstractThread getThread(SendThreadQO qo) {
        if (qo.getType().equals(1)) {
            return new FileParseSenderThread(qo);
        } else if (qo.getType().equals(2)) {
            return new RandomSenderThread(qo);
        }
        throw new IllegalArgumentException("找不到相应服务客户端");
    }

    @Getter
    @AllArgsConstructor
    enum Type {
        thread1("文件下发", 1),
        thread2("指定下发", 2),
        thread3("策略下发", 3),
        thread4("随机下发", 4);
        private String name;
        private Integer value;

        public Integer getValue() {
            return value;
        }
    }
}
