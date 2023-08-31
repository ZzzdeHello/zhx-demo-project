package lambda.mylambda;

import java.util.ArrayList;
import java.util.List;

public class MainTestCenter {
    public static void main(String[] args) {
        People p1 = new People("小红",25000);
        People p2 = new People("小白",5000);
        People p3 = new People("小强",1500);
        People p4 = new People("小智",100);
        List<People> list = new ArrayList<>();
        list.add(p1);list.add(p2);list.add(p3);list.add(p4);



//        for( People p : list){
//            ContentCenter ccTrainWay = new ContentCenter(new TrainWay());
//            String s = ccTrainWay.executeTravalWay(p.getMoney());
//            System.out.println(p.name + "可以"+s);
//            ContentCenter ccBicycleWay = new ContentCenter(new BicycleWay());
//            String s2 = ccBicycleWay.executeTravalWay(p.getMoney());
//            System.out.println(p.name + "可以"+s2);
//            ContentCenter ccCarSelfWay = new ContentCenter(new CarSelfWay());
//            String s3 = ccCarSelfWay.executeTravalWay(p.getMoney());
//            System.out.println(p.name + "可以"+s3);
//            ContentCenter ccWalkWay = new ContentCenter(new WalkWay());
//            String s4= ccWalkWay.executeTravalWay(p.getMoney());
//            System.out.println(p.name + "可以"+s4);
//        }
    }
}
