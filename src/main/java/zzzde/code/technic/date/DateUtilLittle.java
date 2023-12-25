package zzzde.code.technic.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class DateUtilLittle {
    /**
     * 日期+月份
     * @param sDate、sNum
     * @return
     */
    public static String DateAddMonths(Date sDate, String sNum) {
        //时间添加N个月, 入参: sDate是时间; sNum是月数,可正可负; sReturnType,返回时间类型,如空则默认与sDate相同
        String sNewDate = "";
        String sTimeString="";
        int nYear=0;
        int nMonth=0;
        int nDay=0;
        int nLen=0;
        int nNewM=0;
        int nD31 =31;
        int nD30 =30;
        int nD28 =28;
        int nYm =12;
        String sM31="[1][3][5][7][8][10][12]";	//为31天的月份
        String sM30="[4][6][9][11]";			//为30天的月份
        String sM28="[2]";						//为28天的月份

        if(StringUtils.isBlank(sNum)){//时长月数,为空,默认无穷,时间返回空;
            return sNewDate;
        }

        if(sDate != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(sDate);
            nYear = cal.get(Calendar.YEAR);
            //当前月
            nMonth = (cal.get(Calendar.MONTH))+1;
            //当前月的第几天：即当前日
            nDay = cal.get(Calendar.DAY_OF_MONTH);
            //当前时：HOUR_OF_DAY-24小时制；HOUR-12小时制
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            //当前分
            int minute = cal.get(Calendar.MINUTE);
            //当前秒
            int second = cal.get(Calendar.SECOND);
            sTimeString = " "+ String.valueOf(hour) +":"+ String.valueOf(minute) +":"+String.valueOf(second);
        }
        else{//初始时间为空，则默认当前系统时间
            Calendar CD = Calendar.getInstance();
            CD.setTime(new java.util.Date());
            nYear = CD.get(Calendar.YEAR) ;
            nMonth = CD.get(Calendar.MONTH)+1;
            nDay = CD.get(Calendar.DATE);
            sTimeString = " "+ String.valueOf(CD.get(Calendar.HOUR)) +":"+ String.valueOf(CD.get(Calendar.MINUTE)) +":"+String.valueOf(CD.get(Calendar.SECOND));
        }

        //check isLast day,初始时间,是否是该月的最后一天;2016-5-17
        boolean bLastDay = false;
        if(sM31.indexOf("["+nMonth+"]") >=0)
        {//1,3,5,7,8,10,12月,31D
            if(nDay == nD31) bLastDay = true;
        }
        else if(sM30.indexOf("["+nMonth+"]") >=0)
        {//4,6,9,11月,30D
            if(nDay == nD30) bLastDay = true;
        }
        else if(sM28.indexOf("["+nMonth+"]") >=0)
        {//2月,28/29D
            if(nYear%4 == 0) nD28 = 29;	//整除, 2月在闰年是29天
            if(nDay == nD28) bLastDay = true;
        }

        //Check Month;
        nNewM = nMonth + Integer.parseInt(sNum);
        if(nNewM > nYm)
        {//nNewM>12
            nLen = nNewM/nYm;	//parseInt取整
            nYear = nYear + nLen;	   	//nLen>=0,直接加nLen年
            nMonth = nNewM%nYm;		   	//取余,且>=0,直接为新月份
            if(nMonth == 0)
            {//整除,12月，年份减1
                nYear = nYear - 1;
                nMonth = nYm;
            }
        }
        else if(nNewM < 1)
        {//nNewM<=0,sNum<0
            nLen = nNewM/nYm;	//parseInt取整
            nYear = nYear + nLen;		//nLen<=0
            nYear = nYear - 1;
            nMonth = nYm + nNewM%nYm;	//nNewM%nYm取余,且<0,加12为新月份
        }
        else
        {//0<nNewM<=12
            nMonth = nNewM;				//直接为新月份
        }

        //Check new Day;
        if(sM31.indexOf("["+nMonth+"]") >=0)
        {//1,3,5,7,8,10,12月,31D
            if(nDay >nD31) nDay = nD31;
            else if(bLastDay) nDay = nD31; //初始时间是该月最后一天;2016-5-17
        }
        else if(sM30.indexOf("["+nMonth+"]") >=0)
        {//4,6,9,11月,30D
            if(nDay >nD30) nDay = nD30;
            else if(bLastDay) nDay = nD30; //初始时间是该月最后一天;2016-5-17
        }
        else if(sM28.indexOf("["+nMonth+"]") >=0)
        {//2月,28/29D
            if(nYear%4==0) nD28 = 29;	//整除, 2月在闰年是29天
            if(nDay >nD28) nDay = nD28;
            else if(bLastDay) nDay = nD28; //初始时间是该月最后一天;2016-5-17
        }

        if(nMonth<10)
            sNewDate = String.valueOf(nYear) +"-0"+ String.valueOf(nMonth) +"-"+ String.valueOf(nDay) + sTimeString;
        else
            sNewDate = String.valueOf(nYear) +"-"+ String.valueOf(nMonth) +"-"+ String.valueOf(nDay) + sTimeString;
        return sNewDate;
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        Date nowDate = calendar.getTime();
        String nowDateStr = simpleDateFormat.format(nowDate);
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String lastDateStr = format.format(date);//上个月
        System.out.println(nowDateStr+"----------"+lastDateStr);
    }
}
