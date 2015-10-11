package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.naicha.app.utils.StringTools;


public class TestCalcTime {
	public static void main(String[] args) {
		String dString = "2015-1-17 11:18:43";
		Date date = StringTools.StringToDatetime(dString);
		SimpleDateFormat sdf = new SimpleDateFormat("");
		sdf.format(new Date());
		int d= date.getDate();
		int m =  date.getMonth();
		int min = date.getMinutes();
		System.out.println(d);
		System.out.println(m);
		System.out.println(min);
		Date nowDate = new Date();
		nowDate.getTime();
		
	}
	
	private String calctime(Date date){
		Date d = (Date)date;
		Long t= d.getTime();
		Long now = new Date().getTime();
		  long ss=(now-t)/(1000); //共计秒数  
		  int MM = (int)ss/60;   //共计分钟数  
		  int hh=(int)ss/3600;  //共计小时数  
		  int dd=(int)hh/24;   //共计天数 
		  if(dd>0){
			  if(dd==1){
				  return "昨天";
			  }else{
				  return dd+"天前"; 
			  }
		  }else if(hh>0){
			 return hh+"小时前";
		  }else if(MM>0){
			 return MM +"分钟前";
		  }else{
			 return "刚刚"; 
		  }
	}
}
