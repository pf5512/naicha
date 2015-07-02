package com.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws IOException {
		// String mobiles = "15738010400";
		// Pattern p = Pattern.compile("^(15[^4,\\D])$");
		//
		// Matcher m = p.matcher(mobiles);
		// System.out.println(m.matches()+"---");
		URL url2 = new URL(
				"http://192.168.1.105:8080/sihu/login/validate.do?phone=13538140693&password=123123");
		InputStreamReader isr = new InputStreamReader(url2.openStream());
		char[] buffer = new char[1024];
		StringBuffer sb = new StringBuffer();
		while (isr.read(buffer) != -1) {
			sb.append(buffer);
		}
//		System.out.println(sb.toString());
//		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(sb.toString());
			String code =  jsonObject.getString("code");	
			System.out.println("解析出来的结果"+code);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
