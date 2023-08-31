package thread.multithreading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 多线程读取文件
public class ThreadParseFile {

    public static void main(String[] args) throws Exception {
        System.out.println("start-----------");
        long t1 = System.currentTimeMillis();

        // String localFilePath = localTempPath+"/"+fileName;
        String localFilePath = "D:\\营服协同_项目\\smt-bss-cooperate-realtime-mkt-inbound-dubbo-project\\smt-bss-cooperate-realtime-mkt-service\\file\\ccView\\QD_YF_20210706_160216.dat";

        // 开启固定线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        // 逐行读取本地文件
        List<String> dataList = new ArrayList<>();

        File f = new File(localFilePath);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(f), "utf-8");
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        // 定义计数器
        int i = 0;
        while ((str = br.readLine()) != null) {
            // i的值是从1开始
            i++;
            // 加入集合
            dataList.add(str);
            if (i % 5000 == 0) {
                //五千行执行一个线程
                System.out.println("i"+i);
                // 每次传入线程的集合
                List<String> parcelList = new ArrayList<>();
                parcelList.addAll(dataList);
                // 清空集合
                dataList.clear();
                // 开启线程
                Runnable task = new BatchHandlerThreadTask(parcelList);
                exec.submit(task);
            }
        }
        reader.close();
        br.close();

        // 判断最后一次
        if (dataList.size() != 0) {
            Runnable task = new BatchHandlerThreadTask(dataList);
            exec.submit(task);
        }

        exec.shutdown();
        while (true) {
            if (exec.isTerminated()) {
                System.out.println("全部线程都结束了，i: "+i+"耗时："+(System.currentTimeMillis()-t1));
                break;
            }
        }

    }


    public static class BatchHandlerThreadTask implements Runnable {
//    protected static Logger logger = LoggerFactory.getLogger(BatchHandlerThreadTask.class);

        //待处理数据集合
        private List dataList;

        public BatchHandlerThreadTask() {
            super();
        }

        public BatchHandlerThreadTask(List dataList) {
            super();
            this.dataList = dataList;
        }

        public List getDataList() {
            return dataList;
        }

        public void setDataList(List dataList) {
            this.dataList = dataList;
        }

        @Override
        public void run() {
            long t1 = System.currentTimeMillis();
            for (int y = 0; y < dataList.size(); y++) {
                String s = (String) dataList.get(y);
                try {
                    System.out.println(s);
                    System.out.println("调用方法");
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("--h--线程名: " + Thread.currentThread().getName() + "--当前线程耗时：" + (System.currentTimeMillis() - t1) + "--当前批次处理总数据" + dataList.size());
        }

    }
}
