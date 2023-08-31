package asynFunction;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 本地测试的时候，异步方法相当于起了一个新线程，主线程需要等待，不然main方法直接 销毁jvm 导致测试方法停止
public class AsynFileParser {
    public static void main(String[] args) {
        // 异步执行版本
        try {
            String path = "C:\\Users\\wengc\\Downloads";
            String fileName = "demo.txt" ;
            File file = new File(path,fileName);
            FileInputStream inputStreamBak = new FileInputStream(file);
            // uploadFileAndInvoke(inputStreamBak);
            updateFile(inputStreamBak);
            System.out.println("main 主线程");
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步：ftp服务器文件上传方法
     * 异步：调用dubbo方法，防止文件还未传完就被service方法解析
     * @param inputStream 输入流
     * @throws Exception
     * @return true 表示上传成功
     */
    public static CompletableFuture<Boolean> uploadFileAndInvoke(FileInputStream inputStream) {

        return CompletableFuture.supplyAsync(() -> {
            boolean b = true ;
            System.out.println(new Date() + "模块ftp文件上传服务器动作----开始----");
            //连接ftp
            try {
                int read = inputStream.read();
                System.out.println(read);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(inputStream);
            //调用dubbo服务，异步解析文件。

            System.out.println(new Date() + "模块ftp文件上传服务器动作----结束----");
            return b;
        });
    }

    public static CompletableFuture updateFile(FileInputStream inputStream){

        return CompletableFuture.runAsync(()->{
            boolean b = true ;
            System.out.println(new Date() + "模块ftp文件上传服务器动作----开始----");
            //连接ftp
            try {
                int read = inputStream.read();
                System.out.println(read);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(inputStream);
            //调用dubbo服务，异步解析文件。

            System.out.println(new Date() + "模块ftp文件上传服务器动作----结束----");
        });
    }


}
