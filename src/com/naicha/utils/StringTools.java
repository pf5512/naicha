package com.naicha.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class StringTools {

	public static Date StringToDatetime(String string){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    GregorianCalendar calendar = new GregorianCalendar();
	    try {
			calendar.setTime(format.parse(string));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    Date date = calendar.getTime();
	    return date;
	}
}
