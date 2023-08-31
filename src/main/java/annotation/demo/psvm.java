package annotation.demo;


/**
 * 注解的使用：
 *
 */
public class psvm {
    public static void main(String[] args) {
        HomeActivity homeActivity = new HomeActivity();
        homeActivity.onCreate();
        System.out.println(homeActivity +"---:"+ homeActivity.getCar().toString());
    }
}
