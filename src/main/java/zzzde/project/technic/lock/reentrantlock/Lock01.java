package zzzde.project.technic.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock01 {
    private static final Lock lock = new ReentrantLock(false);

    public static void main(String[] args) {
        new Thread ( () -> test(),"zzzde.project.technic.thread-001").start();
        new Thread ( () -> test(),"zzzde.project.technic.thread-002").start();
        new Thread ( () -> test(),"zzzde.project.technic.thread-003").start();
        new Thread ( () -> test(),"zzzde.project.technic.thread-004").start();
    }

    public static void test(){
        for (int i = 0 ; i<2 ;i++){
            try{
                lock.lock();//加锁
                System.out.println(Thread.currentThread().getName() + ":正获取锁");
                Thread.sleep( 1000L );
            }catch (Exception exception ){
                exception.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + ":正释放锁");
                lock.unlock();
            }
        }
    }
}
