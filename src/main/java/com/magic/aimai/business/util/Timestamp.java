package com.magic.aimai.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * 功能：UNIX时间戳转换工具 获取当前日期 包括星期几  作者：李作焕 时间：2015-4-20下午2:10:32
 */
public class Timestamp {
	/**
	 * 转换成UNIX时间戳
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int timesTamp(Date date,String formats) throws ParseException {
		// 获取系统时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				formats);
		String time = simpleDateFormat.format(date);
		int timeStemp = (int) (simpleDateFormat.parse(time).getTime() / 1000);
		return timeStemp;
	}

	/**
	 * 计算年 (增加或者减少)
	 * @param date 时间
	 * @param num 年数 正数或者负数
	 * @return
	 * @throws ParseException
	 */
	public static Date calculateYear (Date date,Integer num) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//        calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.add(Calendar.YEAR, num);
		date = calendar.getTime();
		System.out.println(date);
		return date;
	}


	/**
	 * String转UNIX时间戳
	 * @param dateTime
	 * @return
	 * @throws ParseException
	 */
	public static Integer timesStr(String dateTime,String formats) throws ParseException{
		Date date = new SimpleDateFormat(formats).parse(dateTime);
		int unixTimestamp = (int) (date.getTime()/1000);
		return unixTimestamp;
	}
	/**
	 * 转换成Date
	 * @param timestampString
	 * @return
	 */
	public static Date parseDate(String timestampString,String formats){
		Date date = null;
		Long timestamp = Long.parseLong(timestampString) * 1000;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formats);
		String dates = simpleDateFormat.format(timestamp);
		try {
			date = simpleDateFormat.parse(dates);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 转换成Date
	 * @param timestampString
	 * @return
	 */
	public static Date parseDate2(String timestampString,String formats){
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formats);
		try {
			date = simpleDateFormat.parse(timestampString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 转换成指定时间格式
	 *
	 * @param timestampString
	 * @return
	 */
	public static String TimeStamp2Date(String timestampString,String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats)
				.format(new Date(timestamp));
		return date;
	}

	/**
	 *  转换 日期 string 指定时间格式
	 * @param date
	 * @return
	 */
	public static String DateTimeStamp(Date date,String formats){
		SimpleDateFormat format = new SimpleDateFormat(formats);
		return format.format(date);
	}



	/**
	 * * 获取指定日期是星期几 参数为null时表示获取当前日期是星期几
	 *
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		/*SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String time = simpleDateFormat.format(date);*/
		String[] weekOfDays = { "周末", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String times(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String time = simpleDateFormat.format(date);
		return time;
	}
	/**
	 * 封装当前日期和星期几
	 * @return
	 */
	public static String weekTime(){
		Date date = null;
		String getWeek = getWeekOfDate(date);
		String time = times();
		return time+getWeek;
	}


	public static Date setDateHH(Date date){
		if(null == date){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		return calendar.getTime();
	}


	public static void main(String[] args) throws ParseException {

	}
}
