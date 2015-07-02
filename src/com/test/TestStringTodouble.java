package com.test;

public class TestStringTodouble {
	public static void main(String[] args) {
		String location = "12312.123,12312.11";
		String[] xy=location.split(",");
		double x = new Double(xy[0]); 
		double y = new Double(xy[1]);
		System.out.println("x:"+x);
		System.out.println("y:"+y);
	}
}
