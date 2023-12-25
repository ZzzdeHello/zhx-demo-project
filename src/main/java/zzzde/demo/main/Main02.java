package zzzde.demo.main;

import java.util.concurrent.TimeUnit;

public class Main02 {
    public static void main(String[] args) {
        long convert = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES);
        System.out.println(convert);
    }
}
