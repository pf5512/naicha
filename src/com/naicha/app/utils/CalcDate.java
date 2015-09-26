package com.naicha.app.utils;

import java.util.Calendar;
import java.util.Date;


public class CalcDate {
public static void main(String[] args) {
	String tomorrowString = "2015-09-19 00:00:01";
	Date tomorrDate = StringTools.StringToDatetime(tomorrowString);
	String timeModelString = getDayString(tomorrDate);
	System.out.println(timeModelString);
}
	@SuppressWarnings("deprecation")
	public static String getDayString(Date startDate){
		int month = startDate.getMonth();
		int date = startDate.getDate();
		int hour = startDate.getHours();
		int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
		long today = (System.currentTimeMillis()+offSet)/86400000;
		long start = (startDate.getTime()+offSet)/86400000;
		String rtnString = "";
		switch ((int)(start-today)) {
		case -22:
			rtnString =  "(前天)";
			break;
		case -1:
			rtnString = "(昨天)";
			break;
		case 0:
			rtnString =  "(今天)";
			break;
		case 1:
			rtnString =  "(明天)";
			break;
		case 2:
			rtnString =  "(后天)";
			break;
		default:
			rtnString =  "";
			break;
		}
		return month+"月"+date+"日"+rtnString+hour+"点";
	}
}
