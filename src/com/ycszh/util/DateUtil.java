package com.ycszh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @包名:com.ycszh.util
 * @文件名:DateUtil.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class DateUtil {
	/**
	 * 将日期转化为字符串格式为"yyyy-MM-dd"
	 * 
	 * @param date
	 * @return String
	 */
	public static String date2String(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 将日期转化为指定格式字符串
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String date2String(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 获取当天零点的时间
	 */
	public static String date2ld() {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat format=null;
		Date date=null;
		cal.add(Calendar.DAY_OF_MONTH, 0);
		date=cal.getTime();
		format=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String e_date=format.format(date);
		return e_date;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {

		return new Date(System.currentTimeMillis());
	}

	
	/**
	 * 获取系统几天前前时间(正数为day天后，负数为day天前)
	 * 
	 * @param day
	 * @return Date
	 */
	public static Date getAppointDate(int day){
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        Date date = calendar.getTime();
        return date;
	}
	
	/**
	 * 获取系统几月前前日期(正数为month天前，负数为month天后)
	 * 
	 * @param day
	 * @return Date
	 */
	public static Date getMonthDate(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 获取系统几天前前时间(正数为day天前，负数为day天后)
	 * 
	 * @param day
	 * @return Date
	 */
	public static Date getAppointDateByHour(int hour) {

		return new Date(System.currentTimeMillis() - hour * 3600 * 1000);

	}

	/**
	 * 将日期转化为字符串格式为"yyyy-MM-dd"
	 * 
	 * @param date
	 * @return String
	 */
	public static Date string2Date(String str) {
		if (str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将日期转化为字符串格式为format
	 * 
	 * @param str
	 * @param format
	 * @return String
	 */
	public static Date string2Date(String str, String format) {
		if (null == str || str.equals(""))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取id
	 * 
	 * @param args
	 * @return String
	 */
	public static String grantId() {
		Date date = new Date();
		String id = "s" + date2String(date, "yyyyMMddHHmmss");
		return id;
	}

	/**
	 * 获取id
	 * 
	 * @param args
	 * @return String
	 */
	public static String grantString() {
		Date date = new Date();
		return date2String(date, "yyyyMMddHHmmssSSS");
	}
    /**
     * 获取某一日期的前后几天的日期
     * @param date 
     * @param day 
     * @return
     */
	public static String getDateBefore(Date date,int day){
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE)-day);
		Date resultDate=now.getTime();
		return DateUtil.date2String(resultDate);
	}
	
	public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(getAppointDate(-15));
	}
}
