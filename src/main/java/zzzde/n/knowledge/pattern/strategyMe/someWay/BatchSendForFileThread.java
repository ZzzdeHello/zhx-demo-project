package zzzde.n.knowledge.pattern.strategyMe.someWay;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import zzzde.n.knowledge.pattern.strategyMe.SendQO;
import zzzde.n.knowledge.pattern.strategyMe.SendThreadWay;

//具体策略类 01
@Data
public class BatchSendForFileThread implements SendThreadWay {


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
        System.out.println("选择批量文件派单的方法");
    }
}
