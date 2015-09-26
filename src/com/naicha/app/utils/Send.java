package com.naicha.app.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class Send {

    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
//    	String phoneString = "18060481475";
    	String phoneString = "13538140693";
    	String PostData = "sname=DL-wangjf&spwd=wangjf12&scorpid=&sprdid=1012818&sdst="+phoneString+"&smsg="+java.net.URLEncoder.encode("您的验证码是：5756。请不要把验证码泄露给其他人。【微网通联】","utf-8");
        String ret =Send.SMS(PostData, "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit");
        System.out.println(ret);
        System.out.println(phoneString);
        //请自己反序列化返回的字符串并实现自己的逻辑
	}
}
