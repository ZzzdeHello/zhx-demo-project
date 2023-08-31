package pattern.strategyPattern.someWay;

import pattern.strategyPattern.OrderSendThreadWay;

import java.util.HashMap;
import java.util.Map;

//具体策略类 01
public class BatchSendForFileThread implements OrderSendThreadWay {

    private Map<String, Object> threadParam = new HashMap();

    @Override
    public Map<String, Object> setParam(Map<String, Object> param) {
        return this.threadParam = param;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("选择批量文件派单的方法");
    }
}
