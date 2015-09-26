package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcAge {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		  Date date=new Date();     
		  Date mydate= myFormatter.parse("2014-09-03");
		  long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
		  Integer year=(int) (day/365);
		System.out.println("niu:" + year);
	}
	public Integer getAge(Date mydate){
	  Date date=new Date();     
	  long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
	  Integer year=(int) (day/365);
	return year;
	}
}
