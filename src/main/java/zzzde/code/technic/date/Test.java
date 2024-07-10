package zzzde.code.technic.date;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


public class Test {

    public static void main(String[] args) {

        try {
            String startTimeStr = "2024-05-09 22:00:00:222";
            String endTimeStr = "2024-06-19 22:00:00:222";
            Date startTime = DateUtil02.getDateFromPattern(startTimeStr, "yyyy-MM-dd HH:mm:ss");
            Date endTime = DateUtil02.getDateFromPattern(endTimeStr, "yyyy-MM-dd HH:mm:ss");

            DateUtil.rangeConsume(startTime, endTime, DateField.DAY_OF_YEAR, datetime -> {
                Instant instant = datetime.toInstant();

                LocalDate localDate = LocalDateTimeUtil.ofDate(datetime.toInstant()); // 某天
                System.out.println("month : " + localDate.getDayOfMonth());
                System.out.println("week : " + localDate.getDayOfWeek().getValue());
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
