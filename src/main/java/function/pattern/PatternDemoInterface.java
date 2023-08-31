package function.pattern;

public interface PatternDemoInterface {
    public <T> String test(String str , T t );
    public <T,E> String test2(String str , T t ,E e);
}
