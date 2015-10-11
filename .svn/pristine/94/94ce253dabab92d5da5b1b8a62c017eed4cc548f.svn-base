package com.test;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONTest {
	public static void main(String[] args) throws JSONException {
		String json = "{\"code\":\"-1\",\"msg\":\"手機號碼已經被註冊了\"}";
		JSONObject object = new JSONObject(json);
		String str = (String) object.get("msg");
		System.out.println(str);
	}
}
