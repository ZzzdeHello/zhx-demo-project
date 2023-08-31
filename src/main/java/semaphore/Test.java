package semaphore;

import java.util.concurrent.Semaphore;

// 信号量 .并发量控制
public class Test {
    private static Semaphore semaphore = new Semaphore(200, false);

    public static void main(String[] args) {
        for(int i  = 0 ; i< 10 ;i ++ ){
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
        System.out.println("");

    }
}
