package zzzde.project.technic.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//测试线程池各个参数字段含义的方法
public class TheadDemo {
    private static volatile ThreadPoolExecutor localThreadPool;// 本地线程池
    private static volatile ThreadPoolExecutor localThreadPoolWithHandler;// 本地线程池
    static {
        localThreadPool = new ThreadPoolExecutor(2,10,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(Integer.MAX_VALUE));
    }
    static {// 携带拒绝策略的线程池
        localThreadPoolWithHandler = new ThreadPoolExecutor(2,10,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(Integer.MAX_VALUE),new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public static void main(String[] args) {
        for (int i = 0 ; i <20; i++){
            NameThread thread = new NameThread();
            thread.setName("Thread" + i);
            localThreadPool.execute(thread);
        }

    }

    private static class NameThread implements Runnable {
        private String name = "";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
