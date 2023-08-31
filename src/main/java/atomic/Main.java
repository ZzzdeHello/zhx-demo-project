package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger() ;
        atomicInteger.set(0);

        Thread[] threads = new Thread[20];

        for (int i = 0 ; i <20 ;i++ ) {
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        atomicInteger.incrementAndGet();//自增1
                        System.out.println( Thread.currentThread().getName()+"线程+1;结果为：" + atomicInteger.get());
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(atomicInteger);
    }
}
