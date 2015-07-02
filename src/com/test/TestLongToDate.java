package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLongToDate {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeStart = sdf.parse("2011-09-20 12:30:45").getTime();
		System.out.println(timeStart);
		Date date = new Date(timeStart);
		System.out.println(sdf.format(date));
	}
}
