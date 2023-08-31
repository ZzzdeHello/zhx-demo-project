package lambda.mylambda;

public class TrainWay implements TravelWayInterface {
    @Override
    public String goOutMethod(int money) {
        if(money > 10000) return "火车出游";
        else return "不适合火车游";
    }
}
