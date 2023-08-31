package lambda.mylambda;

public class People {
    String name;
    int money;

    public People() {
    }

    public People(String name, int money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
