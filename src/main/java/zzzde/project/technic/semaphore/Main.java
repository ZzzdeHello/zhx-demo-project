package zzzde.project.technic.semaphore;

import java.util.concurrent.*;

/**
 * @Author zzzde
 * @Date 2023/10/10
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //令牌桶，信号量实现，容量为3
        final Semaphore semaphore = new Semaphore(3);

        //等待，等候令牌桶储存
        Thread.sleep(5);
        //模拟洪峰5个请求，前3个迅速响应，后两个排队
        for (int i = 0; i < 5; i++) {
            semaphore.acquire();
            System.out.println("洪峰：" + i);
        }
        //模拟日常请求，2s一个
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            semaphore.acquire();
            System.out.println("日常：" + i);
            Thread.sleep(1000);
        }
        //再次洪峰
        for (int i = 0; i < 5; i++) {
            semaphore.acquire();
            System.out.println("洪峰：" + i);
        }
        //检查令牌桶的数量
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            System.out.println("令牌剩余：" + semaphore.availablePermits());
        }
    }
}