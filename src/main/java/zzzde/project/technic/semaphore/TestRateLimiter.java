package zzzde.project.technic.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class TestRateLimiter implements Runnable {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Semaphore semaphore = new Semaphore(200, false ); // 允许每秒最多1个任务


    public static void main(String[] arg) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {
                semaphore.acquire(); // 请求令牌,超过许可会被阻塞
                Thread thread = new Thread(new TestRateLimiter());
                thread.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("时间差" + (start - end) +"毫秒");
    }

    public void run() {
        System.out.println(sdf.format(new Date()) + " Task End..");
    }
}