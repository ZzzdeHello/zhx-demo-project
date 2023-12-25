package zzzde.project.technic.thread.threadlocal;

public class Demo {
    public static void main(String[] args) {
        Demo sn = new Demo();

        //③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();

    }

    //①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 10;
        }
    };

    //②获取下一个序列值
    public int getNextNum(){ // ThreadLocal 内部方法set，设置下一个初始值加1.
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    private static class TestClient extends Thread
    {
        private Demo sn; // 包括了一个匿名内部静态ThreadLocal类
        public TestClient(Demo sn) {//线程构造方法
            this.sn = sn;
        }
        public void run()
        {
            //④每个线程打出3个序列值
            for (int i = 0; i < 3; i++) {
                System.out.println("zzzde.project.technic.thread["+Thread.currentThread().getName()+
                        "] sn["+sn.getNextNum()+"]");
            }
        }//线程执行逻辑
    }
}
