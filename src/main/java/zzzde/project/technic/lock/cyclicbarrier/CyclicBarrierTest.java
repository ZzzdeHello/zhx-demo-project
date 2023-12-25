package zzzde.project.technic.lock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// CyclicBarrier的计数器可以使用reset()方法重置，可以使用多次，所以CyclicBarrier能够处理更为复杂的场景 .
// CountDownLatch允许一个或多个线程等待一组事件的产生，而CyclicBarrier用于等待其他线程运行到栅栏位置 .
public class CyclicBarrierTest {
    private static class NormalTask implements Runnable {
        CyclicBarrier barrier;

        NormalTask(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "时间" + System.currentTimeMillis());
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " first step finished");

        }
    }

    private static class FinalTask implements Runnable {
        public FinalTask() {

        }

        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " second step finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //第一个2表示当有2个线程调用await方法时解锁，第二个参数是解锁后优先执行线程
        CyclicBarrier barrier = new CyclicBarrier(2, new FinalTask()); // 2 为屏障拦截的线程数， finalTask为运行的新的线程
        new Thread(new NormalTask(barrier), "线程1").start();
        new Thread(new NormalTask(barrier), "线程2").start();
        //线程1时间1665389938302
        //线程2时间1665389938302
        //1665389942313 second step finished
        //1665389942313 first step finished
        //1665389942314 first step finished
        // 运行结果分析：线程1,2 调用逻辑一直执行到await方法，然后等待，直到达到两个线程wait，执行CyclicBarrier构造器定义的 FinalTask
        // 之后屏障打开，所有线程通过继续执行，所以打印最后两行结果

        test(); // 如果不设置优先执行的线程
    }

    public static void test(){
        CyclicBarrier barrier = new CyclicBarrier(2); // 2 为屏障拦截的线程数
        new Thread(new NormalTask(barrier), "线程1").start();
        new Thread(new NormalTask(barrier), "线程2").start();
        new Thread(new FinalTask()).start();
        //线程2时间1665390339965
        //线程1时间1665390339965
        //1665390339966 first step finished
        //1665390339966 first step finished
        //1665390341960 second step finished
    }
}
