package zzzde.demo.main;

import cn.hutool.core.date.DateUtil;

import java.util.Calendar;

/**
 * @Author zzzde
 * @Date 2024/3/7
 */
public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterdayStr = DateUtil.formatDate(cal.getTime());
        System.out.println(yesterdayStr);
    }
}
