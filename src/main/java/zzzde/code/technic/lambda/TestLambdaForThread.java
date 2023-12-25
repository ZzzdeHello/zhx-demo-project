package zzzde.code.technic.lambda;

public class TestLambdaForThread {

    //利用Lambda语法书写匿名内部类
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(2 + ":" + i);
            }
        });
        t.start();
    }
}
