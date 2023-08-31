package lambda.mylambda;

public class WalkWay implements TravelWayInterface {
    @Override
    public String goOutMethod(int money) {
        if(money > 200) return "步行出游";
        else return "不适合出游";
    }
}
