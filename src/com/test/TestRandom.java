package com.test;

import java.util.Random;

public class TestRandom {
	public static void main(String[] args) {
		Random r = new Random();
		int a = r.nextInt(9999);
		String str = a+"";
		while(str.length()<4){
			str ="0"+str;
		}
		System.out.println(str);
	}
}
