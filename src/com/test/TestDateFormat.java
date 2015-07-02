package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestDateFormat {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(format.parse("2015-01-17 20:09:28"));
//	    calendar.add(Calendar.DAY_OF_YEAR, 1);

	    Date date = calendar.getTime();
	    System.out.println(date);
	}
}
