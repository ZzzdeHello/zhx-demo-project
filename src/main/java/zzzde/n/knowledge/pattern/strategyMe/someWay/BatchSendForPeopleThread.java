package zzzde.n.knowledge.pattern.strategyMe.someWay;

import com.alibaba.fastjson.JSON;
import zzzde.n.knowledge.pattern.strategyMe.SendQO;
import zzzde.n.knowledge.pattern.strategyMe.SendThreadWay;

//具体策略类 02
public class BatchSendForPeopleThread implements SendThreadWay {


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
        System.out.println("选择批量派单到人的方法");
    }
}
