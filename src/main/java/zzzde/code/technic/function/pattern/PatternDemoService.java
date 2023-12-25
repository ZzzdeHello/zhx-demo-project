package zzzde.code.technic.function.pattern;

public class PatternDemoService implements PatternDemoInterface {

    @Override
    public <T> String  test(String str, T t) {
        String res = t + str ;
        System.out.println("method");
        return res;
    }

    @Override
    public <T,E> String test2(String str, T t, E e) {
        return null;
    }

    public static void main(String[] args) {
        PatternDemoService p = new PatternDemoService();
        String test = p.test("s", "t");
        System.out.println(test);
    }
}
