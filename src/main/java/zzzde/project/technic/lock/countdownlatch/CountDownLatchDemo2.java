package zzzde.project.technic.lock.countdownlatch;

import java.util.concurrent.CountDownLatch;

//统一起跑，线程并行。
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(5);

        for (int i=0; i< 5; i++) {
            new Thread(new MyRunnable(countDownLatch, await)).start();  // 将两个计数器统一传入每个线程中，
                                                                        // 1、countDownLatch等待主线程统一发送指令。
                                                                        // 2、await控制 同时也要有5位赛跑运动员才开始跑
        }

        System.out.println("主线程处理自己事情");
        Thread.sleep(3000);
        countDownLatch.countDown(); // 主线程发号施令
        System.out.println("主线程处理结束");
        await.await();
        System.out.println("主线程知道->子线程处理完毕啦");
    }

}

class MyRunnable implements Runnable {

    private CountDownLatch countDownLatch;

    private CountDownLatch await;

    public MyRunnable(CountDownLatch countDownLatch, CountDownLatch await) {
        this.countDownLatch = countDownLatch;
        this.await = await;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();// 利用主线程的计数器 ，将子线程等待
            System.out.println("子线程" +Thread.currentThread().getName()+ "处理自己事情");
            Thread.sleep(1000);
            await.countDown(); // 子线程数量-1 ，达到自己控制的 5个时候，才开始跑
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
