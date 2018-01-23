package com.notebook.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getToday() {
		// 设置时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		// todayDirectory以当前时间新建的目录
		String today = format.format(new Date()).toString();
		return today;
	}

	public static String getTodayFileName() {
		// 设置时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		// todayDirectory以当前时间新建的目录
		String today = format.format(new Date()).toString();
		return today;
	}

	public static String getMonth() {
		// 设置时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		// todayDirectory以当前时间新建的目录
		String month = format.format(new Date()).toString();
		return month;
	}

	public static String getCurrentHour() {
		// 设置时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
		// todayDirectory以当前时间新建的目录
		String hour = format.format(new Date()).toString();
		return hour;
	}

	public static String getCurrentMinute() {
		// 设置时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		// todayDirectory以当前时间新建的目录
		String minute = format.format(new Date()).toString();
		return minute;
	}

	public static String getCurrentSecond() {
		// 设置时间格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		// todayDirectory以当前时间新建的目录
		String second = format.format(new Date()).toString();
		return second;
	}

	public static String getTimeString() {
		Date date = new Date();
		return String.valueOf(date.getTime());

	}

	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(new Date());
		return dateTime;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(date);
		return dateTime;
	}


	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}

