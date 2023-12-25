package zzzde.code.technic.lambda.mylambda;

public class BicycleWay implements TravelWayInterface {
    @Override
    public String goOutMethod(int money) {
        if(money > 2000) return "骑自行车出游";
        else return "不适合骑车游";
    }
}
