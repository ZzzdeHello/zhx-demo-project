package zzzde.project.technic.thread;

public class MyThread {
    public static void main(String[] args) {
        LocalNewThreadObject localNewThreadObject = new LocalNewThreadObject();
        localNewThreadObject.setString("aa");
        Thread thread = new Thread(localNewThreadObject);
        thread.start();
        System.out.println("主线程执行完毕" + System.currentTimeMillis());
        System.out.println("主线程执行完毕");
    }

     static  class LocalNewThreadObject implements Runnable{

        private String string;

        public void setString (String s){
            this.string = s;
        }
        @Override
        public void run() {
            System.out.println("单线程开始" + System.currentTimeMillis());
            // wait(300);
            System.out.println(string);
            System.out.println("单线程结束" + System.currentTimeMillis());
        }
    }
}
