package lambda.mylambda;

public class CarSelfWay implements TravelWayInterface {
    @Override
    public String goOutMethod(int money) {
        if(money > 20000) return "自家出游";
        else return "不适合自驾游";
    }
}
