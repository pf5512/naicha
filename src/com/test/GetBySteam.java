package com.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * 访问网页源码
 * @author asus
 *
 */
public class GetBySteam{
	public static void main(String[] args) {
		onCreate();
	}
    protected static void onCreate() {
        try {
        	String uriAPI = "http://192.168.1.114:8080/naicha/register/save.do?phone=13800138001&password=123123&validatecode=1234";
        	InputStream is=new URL(uriAPI).openStream();
            //读取数据的包装流
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            //str用于读取一行数据
            String str=null;
            //StringBuffer用于存储所欲数据
            StringBuffer sb=new StringBuffer();
            while((str=br.readLine())!=null){
                sb.append(str);
            }
            System.out.println(sb.toString());
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
