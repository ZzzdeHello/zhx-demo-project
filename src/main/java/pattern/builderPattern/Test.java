package pattern.builderPattern;

public class Test {
    public static void main(String[] args) {

        System.out.println( new User.UserBuilder("小明", "朱").address("杭州").phone("1999999999").build());
    }
}
