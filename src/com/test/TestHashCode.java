package com.test;

public class TestHashCode {
	public static void main(String[] args) {
		String url = "http://www.iteye.com/upload/logo/user/729928/9d409596-e279-39ff-b3e8-2e08f8352a79.jpg";
		String fileName = String.valueOf(url.hashCode());
		String fileName2 = String.valueOf(url.hashCode());
		System.out.println(fileName);
		System.out.println(fileName2);
	}
}
