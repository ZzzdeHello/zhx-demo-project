package date;

import java.text.SimpleDateFormat;
import java.util.*;

public class GetLastMonth {

    public static String getLastMonth() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String tempDate = "20200101";
        Date parse = format.parse(tempDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        parse = calendar.getTime();
        String accDate = format.format(parse);
        return accDate;
    }

    public static void main(String[] args) {
        String lastMonth = null;
        try {
            lastMonth = getLastMonth();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(lastMonth);
    }
}
