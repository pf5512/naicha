package com.naicha.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.utils.Codes;

@Controller
@RequestMapping("/pay")
public class PaymentController {
	
	/**
	 * 微信支付：统一下单API
	 * @author yangxujia
	 * @date 2015年10月6日下午5:50:51
	 */
	@RequestMapping("/unifiedorder.do")
	@ResponseBody
	public Map<String, Object> unifiedorder(String appid,String gmch_id,String nonce_str,String sign,String body,
			String out_trade_no,String  total_fee,String spbill_create_ip,String trade_type,
			HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String notify_url = Codes.HOST+"recieverResult";
		/*	
		  <xml>
			   <appid>wx2421b1c4370ec43b</appid>
			   <attach>支付测试</attach>
			   <body>JSAPI支付测试</body>
			   <mch_id>10000100</mch_id>
			   <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
			   <notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url>
			   <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
			   <out_trade_no>1415659990</out_trade_no>
			   <spbill_create_ip>14.23.150.211</spbill_create_ip>
			   <total_fee>1</total_fee>
			   <trade_type>JSAPI</trade_type>
			   <sign>0CB01533B8C1EF103065174F50BCA001</sign>
			</xml>
			*/
		//接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
		/*
		 返回
		 <xml>
   <return_code><![CDATA[SUCCESS]]></return_code>
   <return_msg><![CDATA[OK]]></return_msg>
   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
   <mch_id><![CDATA[10000100]]></mch_id>
   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>
   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>
   <result_code><![CDATA[SUCCESS]]></result_code>
   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>
   <trade_type><![CDATA[JSAPI]]></trade_type>
</xml>
		 
		 */
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	 /**
	  * 微信支付：支付结果通用通知
	  * @author yangxujia
	  * @date 2015年10月7日上午9:16:58
	  */
		@RequestMapping("/unifiedorder2.do")
		@ResponseBody
		public Map<String, Object> unifiedorder2(String return_code,String return_msg,
				HttpServletRequest request){
			if (return_code.equals("SUCCESS")) {
				/*向微信服务器发送成功报文
				 * <xml>
				  <return_code><![CDATA[SUCCESS]]></return_code>
				  <return_msg><![CDATA[OK]]></return_msg>
				  </xml>
				 */
			}
			
		 return null;
	 }
	
}
