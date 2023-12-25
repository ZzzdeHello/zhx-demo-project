package zzzde.code.technic.lambda;

interface ItemCreatorBlankConstruct {
    Item getItem();
}
interface ItemCreatorParamContruct {
    Item getItem(int id, String name, double price);
}

public class TestLambdaForConstructor {
    public static void main(String[] args) {
        //语法一：空参（） -> 加方法（这里是构造器） 无返回值
        ItemCreatorBlankConstruct creator = () -> new Item();
        Item item = creator.getItem();
        //语法二：类名：：加上new 关联后面使用的getItem（就是例如后面creator3使用getItem三个参数构造器的话，
        //                                           需要有对应的构造方法）
        ItemCreatorBlankConstruct creator2 = Item::new;
        Item item2 = creator2.getItem();

        ItemCreatorParamContruct creator3 = Item::new;
        Item item3 = creator3.getItem(112, "鼠标", 135.99);
    }
}