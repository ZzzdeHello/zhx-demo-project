package pattern.builderPattern.interfacePattern;

public class CreateComputer {
    public static void main(String[] args) {
        Director director = new Director();//创建装机人员
        director.setBuilder(new LowComputer()); //告诉装机人员电脑配置，这里为低配版
        director.createComputer(); //装机人员开始组装
        Computer computer = director.getComputer(); //从装机人员获取组装好的电脑
        System.out.println(computer);
    }
}
