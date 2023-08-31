package date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取每月第一天零点、到最后一天23:59:59
 */
public class GetMonthFristEndDay {
    public static void main(String[] args) throws ParseException {
//        Date firstDayOfMonth = getFirstDayOfMonth();
//        Date lastDayOfMonth = getLastDayOfMonth();
//        System.out.println(firstDayOfMonth+"----"+lastDayOfMonth);
//        String lastMonth = getLastMonth();
//        System.out.println(lastMonth);
        Map<String, String> param = new HashMap<>();
        param.put("startDate","20200911");
        try {
            Map test = test(param);
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取当前月第一天
     * @return
     */
    private static Date  getFirstDayOfMonth() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);
        String first = format.format(c.getTime())+"00:00:00";
        Date parse = format2.parse(first);
        return parse;
    }

    private static Date getLastDayOfMonth() throws ParseException {
        // 格式化日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime())+"23:59:59";
        Date parse = format2.parse(last);
        return parse;
    }

    private static String getLastMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date now = new Date();
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.toString());
        instance.setTime(now);
        instance.add(Calendar.MONTH,-1);
        Date lastMonth = instance.getTime();
        String lastMonthStr = format.format(lastMonth);
        return lastMonthStr;
    }

    private static  Map test(Map<String,String> param) throws Exception{
        //根据起始月份判断去哪张表查询 flag 1 为新表 flag 2 为his 表
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.MONTH,-1);
        Date time = instance.getTime();//上月月份时间
        boolean newTable = time.before(simpleDateFormat.parse(param.get("startDate")));
        if(newTable)
            param.put("flag","1");
        else
            param.put("flag","2");
        return param;
    }
}
