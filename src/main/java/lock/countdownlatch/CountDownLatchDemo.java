package lock.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(10);
        for (int i=0; i<3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 运行");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();// 一个线程执行完毕，计数器 -1
                    }
                }
            }).start();
        }
        System.out.println("等待子线程运行结束"); // 主线程等待 子线程全部执行完毕
        boolean await = false;
        try {
            await = latch.await(100, TimeUnit.SECONDS); // 主线程最大等待时间，如果超过这个限制，主动释放主线程，防止一直等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(await){
            System.out.println("子线程运行结束");
        }else {
            System.out.println("子线程运行异常");
        }
        System.out.println("子线程都结束了，await唤醒了主线程");

    }
}
