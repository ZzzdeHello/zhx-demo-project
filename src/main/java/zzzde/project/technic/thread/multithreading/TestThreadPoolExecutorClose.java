package zzzde.project.technic.thread.multithreading;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Java并发编程中在使用到ThreadPoolExecutor时，对它的三个关闭方法（shutdown()、shutdownNow()、awaitTermination()）的异同点如下
 *
 *
 * 总结：
 *  shutdown（）和shutdownNow（）方法的区别就是 后者：会去立刻停止正在跑和正在等待的任务。
 *  AwaitTermination 方法时阻塞的，执行后还是可以提交任务，除非等待时间超时。但是shutdown不允许提交任务
 *
 */
public class TestThreadPoolExecutorClose {

    private static ExecutorService pool = Executors.newFixedThreadPool(3);
    private static Semaphore semaphore = new Semaphore(200, false); //dubbo请求限流
//    public static void main(String[] args) {
//        System.out.println("主方法开始执行");
//        for(int i = 1 ; i < 3;i++ ){
//            pool.execute(new parseFile(i));
//        }
//        pool.shutdown();
//    }
    public static void main(String[] args) {
        System.out.println("主方法开始执行");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for(int i : list ){
            pool.execute( ()->{
                System.out.println(i);
                parseFile(i);
            });
        }
        pool.shutdown();
    }

//    private static class SayHelloRunnable implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                System.out.println("线程开始执行");
//                zzzde.project.technic.semaphore.acquire();
//                Thread.sleep(1000);
//                zzzde.project.technic.semaphore.release();
//                System.out.println("睡眠执行完毕");
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } finally {
//                System.out.println("hello world!");
//            }
//
//        }
//    }


    /*线程池调用这个方法时不会，在接受新的任务，但也不会立刻停止
        1、停止接收外呼submit任务
        2、内部正在执行的任务，会等待执行完成
        3、等待第二步执行完成，然后关闭
    */
    private void LocalTestShutdown(){

    }

    /*
    shutdownNow():
    1、跟shutdown()一样，先停止接收外部提交的任务
    2、忽略队列里等待的任务
    3、尝试将正在跑的任务interrupt中断
    4、返回未执行的任务列表
     */
    private void LocalTestShutdownNow(){

    }

    /*
    当前线程阻塞，直到

    1、等所有已提交的任务（包括正在跑的和队列中等待的）执行完
    2、或者等超时时间到
    3、或者线程被中断，抛出InterruptedException
    4、然后返回true（shutdown请求后所有任务执行完毕）或false（已超时）

     */
    private void LocalTestAwaitTermination(){

    }


    /**
     * 按批次解析文件方法
     */
    private static class parseFile  implements Runnable {

        int batch;

        parseFile(int batch) {
            this.batch = batch;
        }

        @Override
        public void run() {
            FileInputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bfReader = null;
            try{
                String localDir = System.getProperty("user.dir");
                System.out.println(localDir);
                File localFile = new File(localDir);
                File[] listFile = localFile.listFiles();
                if(listFile == null ){
                    System.out.println("listFile为null 直接返回");
                    return;
                }
                for(File parsingFile : listFile ){
                    if(parsingFile.getName().endsWith(".dat") && parsingFile.getName().contains(String.valueOf(batch))){
                        // 解析文件
                        inputStream = new FileInputStream(parsingFile);
                        inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                        bfReader = new BufferedReader(inputStreamReader,5*1024*1024);// 分5MB 缓存文件读取
                        //解析数据
                        String lineData = "";
                        int line = 0 ;
                        while ( (lineData = bfReader.readLine()) !=null ){
                            line++;
                            if(lineData.length()==0){
                                System.out.println("该dat文件内容为空");
                                break;
                            }
                            String[] fields = lineData.split("\\$%\\$",-1);
                            try{
                                Thread.sleep(500);
                                System.out.println("拼接对象");
                            }catch (Exception e){
                                System.out.println("批次" + batch + "的第"+ line +"字段有问题出现异常" );
                                //入数据库
                                continue;
                            }
                            try{
                                semaphore.acquire();
                                // 假装调用了dubbo 服务
                                System.out.println("假装调用了dubbo 服务");
                                Thread.sleep(500);

                            }catch (Exception e) {
                                System.out.println("调用dubbo方法接口调用异常");
                                e.printStackTrace();
                            }finally {
                                semaphore.release();
                            }
                        }
                        boolean delete = parsingFile.delete();
                        if (delete) System.out.println("----[##--##]------删除"+parsingFile.getName()+"成功");
                    }
                    //dat文件处理完成，最后删除flag文件
                    if(parsingFile.getName().endsWith(".flg") && parsingFile.getName().contains(String.valueOf(batch))){
                        boolean delete = parsingFile.delete();
                        if (delete) System.out.println("----[##--##]------删除"+parsingFile.getName()+"成功");
                    }
                }
            }catch (Exception  e) {
                System.out.println("该批次" + batch+ "文件解析失败");
                e.printStackTrace();
            }finally {
                try {
                    if(bfReader != null){
                        bfReader.close();
                    }
                    if(inputStreamReader != null){
                        inputStreamReader.close();
                    }
                    if(inputStream != null){
                        inputStream.close();
                    }
                }catch (Exception e){
                    System.out.println("流关闭异常");
                    e.printStackTrace();
                }

            }
        }
    }

    private static void parseFile(int batch) {
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bfReader = null;
        try {
            String localDir = System.getProperty("user.dir");
            System.out.println(localDir);
            File localFile = new File(localDir);
            File[] listFile = localFile.listFiles();
            if (listFile == null) {
                System.out.println("listFile为null 直接返回");
                return;
            }
            for (File parsingFile : listFile) {
                if (parsingFile.getName().endsWith(".dat") && parsingFile.getName().contains(String.valueOf(batch))) {
                    // 解析文件
                    inputStream = new FileInputStream(parsingFile);
                    inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    bfReader = new BufferedReader(inputStreamReader, 5 * 1024 * 1024);// 分5MB 缓存文件读取
                    //解析数据
                    String lineData = "";
                    int line = 0;
                    while ((lineData = bfReader.readLine()) != null) {
                        line++;
                        if (lineData.length() == 0) {
                            System.out.println("该dat文件内容为空");
                            break;
                        }
                        String[] fields = lineData.split("\\$%\\$", -1);
                        try {
                            Thread.sleep(500);
                            System.out.println("拼接对象");
                        } catch (Exception e) {
                            System.out.println("批次" + batch + "的第" + line + "字段有问题出现异常");
                            //入数据库
                            continue;
                        }
                        try {
                            semaphore.acquire();
                            // 假装调用了dubbo 服务
                            System.out.println("假装调用了dubbo 服务");
                            Thread.sleep(500);

                        } catch (Exception e) {
                            System.out.println("调用dubbo方法接口调用异常");
                            e.printStackTrace();
                        } finally {
                            semaphore.release();
                        }
                    }
                    boolean delete = parsingFile.delete();
                    if (delete) System.out.println("----[##--##]------删除" + parsingFile.getName() + "成功");
                }
                //dat文件处理完成，最后删除flag文件
                if (parsingFile.getName().endsWith(".flg") && parsingFile.getName().contains(String.valueOf(batch))) {
                    boolean delete = parsingFile.delete();
                    if (delete) System.out.println("----[##--##]------删除" + parsingFile.getName() + "成功");
                }
            }
        } catch (Exception e) {
            System.out.println("该批次" + batch + "文件解析失败");
            e.printStackTrace();
        } finally {
            try {
                if (bfReader != null) {
                    bfReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                System.out.println("流关闭异常");
                e.printStackTrace();
            }
        }
    }
}
