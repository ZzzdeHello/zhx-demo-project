package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class DateUtil02 {

	private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30,
			31, 31, 30, 31, 30, 31 };
	private static SimpleDateFormat sdf = new SimpleDateFormat();

	/** 精确到秒的日期格式 */
	public static final String DATE_PATTERN_SECOND = "yyyy-MM-dd HH:mm:ss";

	/** 精确到日的日期格式 */
	public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";

	/** 精确到秒的日期格式 */
	public static final String COUNT_DATE_PATTERN_SECOND = "MM/dd/yyyyy HH:mm:ss";

	//
	public static final String YYYYMMDD = "yyyyMMdd";
	
	public static final String COUNT_DATE_PATTERN_SECOND_STRING = "yyyyMMddHHmmss";

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateSecondFormat(java.util.Date date) {
		return getDateFormat(date, DATE_PATTERN_SECOND);
	}
	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDayFormat(java.util.Date date) {
		return getDateFormat(date, DATE_PATTERN_DAY);
	}


	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getCountFormat(java.util.Date date) {
		return getDateFormat(date, COUNT_DATE_PATTERN_SECOND);
	}
	
	public static synchronized String getAllStringFormat(java.util.Date date) {
		return getDateFormat(date, COUNT_DATE_PATTERN_SECOND_STRING);
	}
	/**
	 * 将秒转换为 xx:xx:xx 格式
	 * 
	 * @param totalSecond
	 *            总秒数
	 * @return xx:xx:xx 格式的时间
	 * @author 张磊
	 */
	public static String formatSecond(long totalSecond) {
		long mTemp, sTemp;
		long h = (long) Math.floor(totalSecond / (60 * 60));

		mTemp = totalSecond - h * 60 * 60;
		long m = (long) Math.floor(mTemp / 60);

		sTemp = mTemp - m * 60;
		long s = sTemp;

		String hour = String.format("%02d", h);
		String minute = String.format("%02d", m);
		String second = String.format("%02d", s);

		return hour + ":" + minute + ":" + second;
	}

	/**
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static synchronized String getDateFormat(java.util.Date date,
			String pattern) {
		synchronized (sdf) {
			String str = null;
			sdf.applyPattern(pattern);
			str = sdf.format(date);
			return str;
		}
	}

	public static String getLastDayOfMonth() {
		// 本月的最后一天
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		String day = simpleFormate.format(calendar.getTime());
		return day;
	}

	public static String getFirstDayOfMonth() {
		// 本月的第一天
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		String lastDay = simpleFormate.format(calendar.getTime());
		return lastDay;
	}

	public static String getLastDayOfMonth(int month) {
		// 获取指定月的最后一天
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		String day = simpleFormate.format(calendar.getTime());
		return day;
	}

	public static String getLastDayOfMonthStr(int year, int month) {
		// 获取指定月的最后一天
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		String day = simpleFormate.format(calendar.getTime());
		return day;
	}

	public static String getFirstDayOfMonth(int month) {
		// 获取指定月第一天
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat(" yyyy-MM-dd ");
		String lastDay = simpleFormate.format(calendar.getTime());
		return lastDay;
	}

	public static String getFirstDayOfMonth(int year, int month) {
		// 获取指定月第一天
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat(" yyyy-MM-dd ");
		String lastDay = simpleFormate.format(calendar.getTime());
		return lastDay;
	}

	public static String getCurrentDay() {
		// 当天
		SimpleDateFormat simpleFormate = new SimpleDateFormat(" yyyy-MM-dd ");
		String curday = simpleFormate.format(new Date());
		return curday;
	}
	public static int getWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	public static String getLastDayOfLastMonth() {
		// 上月的最后一天
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat(" yyyy-MM-dd ");
		String day = simpleFormate.format(calendar.getTime());
		return day;
	}

	public static String getFirstDayOfLastMonth() {
		// 上月的第一天
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat simpleFormate = new SimpleDateFormat(" yyyy-MM-dd ");
		String lastDay = simpleFormate.format(calendar.getTime());
		return lastDay;
	}

	/**
	 * 获取当前月前六个月
	 * 
	 * @param monthNum
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getBeforeNMonth(int monthNum) {
		if (monthNum > 12) {
			monthNum = 12;
		}
		List monthList = new ArrayList();

		java.util.Calendar cal = java.util.Calendar.getInstance();
		int year = cal.get(java.util.Calendar.YEAR);
		int month = cal.get(java.util.Calendar.MONTH);
		StringBuffer bf = new StringBuffer("");
		int i = 0;
		while (month >= 1 && i < monthNum) {
			bf.append(year);
			bf.append(month >= 10 ? month : "0" + month);
			monthList.add(bf.toString());
			i++;
			month--;
			bf.delete(0, bf.length());
		}
		for (int j = 0; j < monthNum - i; j++) {
			bf.append(year - 1);
			bf.append(12 - j >= 10 ? 12 - j : "0" + (12 - j));
			monthList.add(bf.toString());
			bf.delete(0, bf.length());
		}
		return monthList;
	}

	/**
	 * 获取指定日期的月份
	 * 
	 * @param date
	 * @return int
	 */
	public static int getMonth4Int(String date) {
		int month = 0;
		// date = "201110"只解析yyyyMM模式
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyyMM");
		try {
			Date d = simpleFormate.parse(date);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(d);
			month = calendar.get(Calendar.MONTH) + 1;
		} catch (ParseException e) {
		}

		return month;
	}

	public static synchronized int getLastDayOfMonth(int year, int month) {
		if (month < 1 || month > 12) {
			return -1;
		}
		int retn = 0;
		if (month == 2) {
			if (isLeapYear(year)) {
				retn = 29;
			} else {
				retn = dayArray[month - 1];
			}
		} else {
			retn = dayArray[month - 1];
		}
		return retn;
	}

	public static synchronized boolean isLeapYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return isLeapYear(year);
	}
	
	/**
	 * 判断日期格式是否正确
	 * @param 要验证的字符串日期
	 * @param 验证格式
	 * @return boolean  
	 * @author wangww
	 * @date 2018年7月27日上午10:23:11
	 */
	public static synchronized boolean isValidate(String dateStr, String pattern) {
		boolean convertSuccess = true;
		sdf.applyPattern(pattern);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			convertSuccess = false;
			e.printStackTrace();
		}
		return convertSuccess;
	}

	/**
	 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	 * 3.能被4整除同时能被100整除则不是闰年
	 */
	public static synchronized boolean isLeapYear(int year) {
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 4) == 0) {
			return (year % 100) == 0;
		} else {
			return false;
		}
	}

	/**
	 * 获取包括本月在类的N个月
	 * 
	 * @param monthNum
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static java.util.List getNMonth(int monthNum) {
		if (monthNum > 12) {
			monthNum = 12;
		}
		java.util.List monthList = new java.util.ArrayList();
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int year = cal.get(java.util.Calendar.YEAR);
		int month = cal.get(java.util.Calendar.MONTH) + 1;
		StringBuffer bf = new StringBuffer("");
		int i = 0;
		while (month >= 1 && i < monthNum) {
			bf.append(year);
			bf.append(month >= 10 ? month : "0" + month);
			monthList.add(bf.toString());
			i++;
			month--;
			bf.delete(0, bf.length());
		}
		for (int j = 0; j < monthNum - i; j++) {
			bf.append(year - 1);
			bf.append(12 - j >= 10 ? 12 - j : "0" + (12 - j));
			monthList.add(bf.toString());
			bf.delete(0, bf.length());
		}

		return monthList;
	}

	/**
	 * @param date
	 * @return String
	 */
	public static synchronized String getDateDayFormat(java.util.Date date) {
		String pattern = "yyyy/MM/dd";
		return getDateFormat(date, pattern);
	}

	public static String fotmatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static synchronized Date getDateFromPattern(String date,
			String pattern) throws ParseException {
		synchronized (sdf) {
			Date d = null;
			sdf.applyPattern(pattern);
			d = sdf.parse(date);
			return d;
		}
	}

	public static synchronized String fotmatString(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		String s = null;
		if (str != null) {
			try {
				d = formatter.parse(str);
				s = formatter2.format(d);
			} catch (ParseException e) {

			}
		}
		return s;
	}

	public static synchronized String fotmatString(String str,
			String fromPattern, String toPattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(fromPattern);
		SimpleDateFormat formatter2 = new SimpleDateFormat(toPattern);
		Date d = null;
		String s = null;
		if (str != null) {
			try {
				d = formatter.parse(str);
				s = formatter2.format(d);
			} catch (ParseException e) {

			}
		}
		return s;
	}

	/**
	 * 取得当前年月YYYYMM
	 */
	public static String getCurrYealAndMon() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String mon = month < 10 ? "0" + month : month + "";
		return year + mon;
	}

	/**
	 * gc.add(1,1)年份加1
	 */
	public static String getDateCal(int d, int beforeMonth, String date) {
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		try {
			gc.setTime(getDateFromPattern(date, DATE_PATTERN_DAY));
		} catch (Exception e) {
		}
		gc.add(d, beforeMonth);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc
				.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	/**
	 * 获取两个日期之间相差的月份
	 * 
	 * @param start
	 * @param end
	 */
	public static int getMonth(String start, String end) {
		try {
			Date startDate = getDateFromPattern(start, DATE_PATTERN_DAY);
			Date endDate = getDateFromPattern(end, DATE_PATTERN_DAY);

			if (startDate.after(endDate)) {
				Date t = startDate;
				startDate = endDate;
				endDate = t;
			}
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);
			Calendar temp = Calendar.getInstance();
			temp.setTime(endDate);
			temp.add(Calendar.DATE, 1);

			int year = endCalendar.get(Calendar.YEAR)
					- startCalendar.get(Calendar.YEAR);
			int month = endCalendar.get(Calendar.MONTH)
					- startCalendar.get(Calendar.MONTH);

			if ((startCalendar.get(Calendar.DATE) == 1)
					&& (temp.get(Calendar.DATE) == 1)) {
				return year * 12 + month + 1;
			} else if ((startCalendar.get(Calendar.DATE) != 1)
					&& (temp.get(Calendar.DATE) == 1)) {
				return year * 12 + month;
			} else if ((startCalendar.get(Calendar.DATE) == 1)
					&& (temp.get(Calendar.DATE) != 1)) {
				return year * 12 + month;
			} else {
				return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
			}
		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 获取当前时间戳(精确到秒)
	 */
	public static String getTimeStampForSec() {
		synchronized (sdf) {
			String str = null;
			sdf.applyPattern("yyyyMMddHHmmss");
			str = sdf.format(new Date());
			return str;
		}
	}

	/**
	 * 获取当前时间戳（精确到毫秒）
	 */
	public static String getTimeStampForMil() {
		synchronized (sdf) {
			String str = null;
			sdf.applyPattern("yyyyMMddHHmmssSSS");
			str = sdf.format(new Date());
			return str;
		}
	}

	/**
	 * 根据日期date转换字符串类型
	 * 
	 * @param date
	 * @param str
	 *            str类型
	 * @return
	 */
	public static synchronized String date2Str(Date date, String str) {
		return getDateFormat(date, str);
	}

	/**
	 * 
	 * @param d1
	 * @return
	 */
	public static long getTobeDays(Date d1) {
		if (null == d1) {
			return -1;
		}
		Date d2 = getDateDay(new Date());
		d1 = getDateDay(d1);
		
		Calendar ca = new GregorianCalendar();
		ca.setTime(d2);
		Calendar ca2 = new GregorianCalendar();
		ca2.setTime(d1);
		
		long t = ca2.getTimeInMillis() - ca.getTimeInMillis();
		return t/(24*60*60*1000)+1;


//		return ((d1. - d3.getTime()) / 86400000)+1;
	}
	
	/**
	 * 获取d1与当天的毫秒差值
	 * @param d1
	 * @return
	 */
	public static long getTobeMillisecond(Date d1) {
		if (null == d1) {
			return -1;
		}
		d1 = getDateDay(d1);
		Date d2 = new Date();
		
		Calendar ca1 = new GregorianCalendar();
		ca1.setTime(d1);
		ca1.add(Calendar.DAY_OF_YEAR, 1);
		Calendar ca2 = new GregorianCalendar();
		ca2.setTime(d2);
		
		long t = ca1.getTimeInMillis() - ca2.getTimeInMillis();
		return t;
		
		
//		return ((d1. - d3.getTime()) / 86400000)+1;
	}
	
	
	
	public static Date getDateDay(Date date) {
		sdf.applyPattern("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * yyyy年MM月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String getYMDofChina(Date date) {
		StringBuffer dateStr = null;
		if (null == date) {
			return "";
		}
		dateStr = new StringBuffer();
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		dateStr.append(c.get(Calendar.YEAR)).append("年").append(
				c.get(Calendar.MONTH) + 1).append("月").append(
				c.get(Calendar.DATE)).append("日");
		return dateStr.toString();
	}

	/**
	 * 把java.util.Date toString转换成yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date2Str
	 * @return
	 */
	public static String getFormateDate2String(String date2Str) {
		SimpleDateFormat frm = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		try {
			Date date = frm.parse(date2Str);
			return getDateSecondFormat(date);
		} catch (ParseException e) {
			return "";
		}
	}
	
	public static String getlastMonth() {
		// 上月的第一天
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -1); // 得到前一个月
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyyMM");
		String lastmonth = simpleFormate.format(calendar.getTime());
		return lastmonth;
	}
	
	/**
	 * stringToDate "20110113170001"
	 * @return
	 */
	public static String getCurrentTime()
	{
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return  format.format(date);
	}
	
    private static int i = (int)(Math.random()*99)+10;
	
	/**
	 * 获取以yyyymmddHHmmss+2位数字的订单编号
	 * @return
	 */
	public static synchronized String getNo() {
		i++;
		if (i>99) {
			i = 10;
		}
		return DateUtil02.getCurrentTime()+i ;
	}
	/**
	 * 获取当前是几号
	 * @return
	 */
	public static String getCurrentDayOfMonth() {
		Calendar now = Calendar.getInstance();
		int day = now.get(Calendar.DAY_OF_MONTH);
		return  day + "";
	}
	
	/**
	 * 获取当前日期的前一天是几号
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getYesterDayOfMonth() {
		Calendar now = Calendar.getInstance();
		now.add(now.DATE,-1);
		int day = now.get(Calendar.DAY_OF_MONTH);
		return  day + "";
	}
	
	/**
	 * 获取规定格式的日期
	 */
	public static String getFormatDate(String format) {
		sdf.applyPattern(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取规定格式的前一天日期
	 */
	public static String getYesterDayFormatDate(String format) {
		Calendar now = Calendar.getInstance();
		now.add(now.DATE,-1);
		sdf.applyPattern(format);
		return sdf.format(now.getTime());
	}
	
	/**
	 * 获取指定日期是几号
	 * 
	 * @param date
	 * @return int
	 */
	public static int getDay4Int(String date) {
		int day = 0;
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d = simpleFormate.parse(date);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(d);
			day = calendar.get(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
		}
		return day;
	}
	
	/**
	 * 获取当前是几小时
	 * @return
	 */
	public static String getCurrentHourOfDay() {
		Calendar now = Calendar.getInstance();
		int hour = now.get(Calendar.HOUR_OF_DAY);
		return  hour + "";
	}
	
	/**
	 * 格林威治时间转化为本地时间
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date greenTimeTranslate(String greenTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			Date date = sdf.parse(greenTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, 8);
			return calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 北京时间转化为格林威治时间
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static String pkingTimeTranslate(Date pkingDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pkingDate);
		calendar.add(Calendar.HOUR, -8);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 
	 * 检查是实时还是延迟发送
     * 满足条件
     * a、当前时间在发送时间范围内
     * b、已经到了推送时间点(如果为空则不需要该条件)  
	 * @param: @param pushTime(格式HH:mm)
	 * @param: @param startTime1(格式HH:mm)
	 * @param: @param endTime1(格式HH:mm)
	 * @param: @param startTime2(格式HH:mm)
	 * @param: @param endTime2(格式HH:mm)
	 */
	public static boolean checkContactTime(String pushTime, String startTime1, String endTime1, String startTime2, String endTime2) {
		Long starTimeStamp1 = null;
		Long endTimeStamp1 = null;
		Long starTimeStamp2 = null;
		Long endTimeStamp2 = null;
    	try {
			if (StringUtils.isBlank(pushTime) 
					&& StringUtils.isBlank(startTime1) && StringUtils.isBlank(endTime1)
					&& StringUtils.isBlank(startTime2) && StringUtils.isBlank(endTime2)) {
				//推送时间为空并且发送时间段为空则直接实时发送
				return true;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Long now = sdf.parse(DateUtil02.getDateFormat(new Date(), "HH:mm")).getTime();
			if (StringUtils.isNotBlank(startTime1)) {
				starTimeStamp1 = sdf.parse(startTime1).getTime();
			}
			if (StringUtils.isNotBlank(endTime1)) {
				endTimeStamp1 = sdf.parse(endTime1).getTime();
			}
			if (StringUtils.isNotBlank(startTime2)) {
				starTimeStamp2 = sdf.parse(startTime2).getTime();
			}
			if (StringUtils.isNotBlank(endTime2)) {
				endTimeStamp2 = sdf.parse(endTime2).getTime();
			}
			if (StringUtils.isBlank(pushTime)) {
				if ((starTimeStamp1 != null && endTimeStamp1 != null && now >= starTimeStamp1 && now <= endTimeStamp1) 
						|| (starTimeStamp2 != null && endTimeStamp2 != null && now >= starTimeStamp2 && now <= endTimeStamp2)) {
					return true;
				}
			} else {
				Date d1 = DateUtil02.getDateFromPattern(pushTime, "yyyy-MM-dd HH:mm:ss");
				Date d2 = new Date();
				if (d1.compareTo(d2) <= 0 && (starTimeStamp1 != null && endTimeStamp1 != null && now >= starTimeStamp1 && now <= endTimeStamp1) 
						|| (starTimeStamp2 != null && endTimeStamp2 != null && now >= starTimeStamp2 && now <= endTimeStamp2)) {
					return true;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
