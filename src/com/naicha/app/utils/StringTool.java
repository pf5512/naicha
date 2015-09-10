package com.naicha.app.utils;

public class StringTool {
	public static boolean isEmpty(String str ){
		
		if(str==null||"".equals(str.trim())){
    		return true;
		}
		return false;
	}
}
