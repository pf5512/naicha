package com.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TestGet {
	public static void main(String[] args) {
		String result = doGet();
		System.out.println(result);
	}
	public static String doGet()  
	{  
	    String uriAPI = "http://192.168.1.114:8080/sihu/register/save.do?phone=13800138001&password=123123&validatecode=1234";  
	    String result= "";  
	//  HttpGet httpRequst = new HttpGet(URI uri);  
	//  HttpGet httpRequst = new HttpGet(String uri);  
	//  创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。  
	    HttpGet httpRequst = new HttpGet(uriAPI);  

	//  new DefaultHttpClient().execute(HttpUriRequst requst);  
	    try {  
	//使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。  
	        HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类  
	        if(httpResponse.getStatusLine().getStatusCode() == 200)  
	        {  
	            HttpEntity httpEntity = httpResponse.getEntity();  
	            result = EntityUtils.toString(httpEntity);//取出应答字符串  
	        // 一般来说都要删除多余的字符   
	            result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
	        }  
	               else   
	                    httpRequst.abort();  
	       } catch (ClientProtocolException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	        result = e.getMessage().toString();  
	    } catch (IOException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	        result = e.getMessage().toString();  
	    }  
	    return result;  
	} 
}
