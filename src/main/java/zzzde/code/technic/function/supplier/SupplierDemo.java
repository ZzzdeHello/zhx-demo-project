package zzzde.code.technic.function.supplier;

import java.util.function.Supplier;

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier<Product> sp = () -> new Product("汽车", 1000000);
        Product product = sp.get();
        System.out.println(product);

        Supplier<String> ss = () -> "免费牛奶";
        Supplier<Integer> si = () -> 1;

        Product product1 = supplySuperMachine(sp);
        String s = supplySuperMachine(ss);
        Integer integer = supplySuperMachine(si);
        System.out.println(product1);
        System.out.println(s);
        System.out.println(integer);

    }

    // 拓展： 根据不同的供应方，获取供应方提供的产品
    // get() 的方法可以去修改，复杂化，实现类似工厂的功能
    public static  <T> T supplySuperMachine(Supplier<T> supplier) {
        System.out.println("supply...");
        return supplier.get();
    }


}
