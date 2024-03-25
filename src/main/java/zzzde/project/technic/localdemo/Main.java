package zzzde.project.technic.localdemo;

import cn.hutool.core.util.NumberUtil;

import java.util.Optional;

/**
 * @Author zzzde
 * @Date 2024/2/22
 */
public class Main {

    public static void main(String[] args) {
        Deo deo = new Deo();
        //int takeBatteryCount = Optional.ofNullable(deo.getNum()).orElse(0);
        int takeBatteryCount = deo.getNum();
        int i = takeBatteryCount + 1;
        System.out.println(i);
    }
}
