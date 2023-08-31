package pattern.strategyMe.someWay;

import com.alibaba.fastjson.JSON;
import pattern.strategyMe.SendQO;
import pattern.strategyMe.SendThreadWay;

import java.util.HashMap;
import java.util.Map;

//具体策略类 00
public class BatchSendThread implements SendThreadWay {


    private SendQO param ;

    @Override
    public void setParam(SendQO sendQO) {
        this.param = sendQO;
    }

    @Override
    public void run() {
        try {
            System.out.println(JSON.toJSONString(param));
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("批量派单默认方法");
    }
}
