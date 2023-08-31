package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String dispatchTime = "2020-10-10 22:00:00:222";
        try {
            Date dispatchTimeDate = DateUtil02.getDateFromPattern(dispatchTime, "yyyy-MM-dd HH:mm:ss");
            System.out.println(dispatchTimeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long time = 1604413349636l;
        Date nwww = new Date(time);
        System.out.println(nwww);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy:HH:mm:ss");
        String format = dateFormat.format(nwww);
        System.out.println(format);
    }

}
