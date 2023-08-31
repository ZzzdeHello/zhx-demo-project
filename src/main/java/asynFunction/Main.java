package asynFunction;

import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("CompletableFutureDemo");
        stopWatch.start();

        // 异步执行版本
        try {
            testCompletableFuture();
            System.out.println("main 主线程");
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
        System.out.println(stopWatch);
    }


    public static CompletableFuture<String> doOneThing() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("doOneThing task Future -----start-----");
                Thread.sleep(2000);
                System.out.println("doOneThing task Future -----end-----");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "doOneThing";
        });
    }

    public static CompletableFuture<String> doOtherThing(String parameter) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("doOtherThing task Future ----------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return parameter + " " + "doOtherThing";
        });
    }

    private static void testCompletableFuture() throws InterruptedException, ExecutionException {
        // 先执行 doOneThing 任务，后执行 doOtherThing 任务
        doOneThing();
        //CompletableFuture<String> resultFuture = doOneThing().thenCompose(Main::doOtherThing);

        // 获取任务结果
        // String doOneThingResult = resultFuture.get();

        // 获取执行结果
        // System.out.println("DoOneThing and DoOtherThing execute finished. result = " + doOneThingResult);
    }


}
