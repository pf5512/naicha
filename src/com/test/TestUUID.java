package com.test;

import java.util.UUID;

public class TestUUID {
	public static void main(String[] args) {
		String token = UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
		System.out.println(token);
	}
}
