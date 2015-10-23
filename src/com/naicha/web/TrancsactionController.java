package com.naicha.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.AlipayResult;
import com.naicha.app.service.ApplyService;
import com.naicha.app.service.PaymentService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;

/**
 * 交易控制
 * @author yangxujia
 * @date 2015年10月20日下午5:56:30
 */
@Controller
@RequestMapping("/transaction")
public class TrancsactionController {
	@Autowired
	private ApplyService applyService;
	@Autowired
	private PaymentService paymentservice;

	/**
	 * 1、支付宝充值
	 * @author yangxujia
	 * @date 2015年10月20日下午5:58:41
	 */
	@RequestMapping("rechargeFromAlipay.do")
	@ResponseBody
	public Map<String, Object> rechargeFromAlipay(String taskIdStr,String userIdStr ,String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(taskIdStr)||StringTool.isEmpty(userIdStr)||StringTool.isEmpty(token)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//2.校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("code", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//do something here
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 微信充值
	 * @author yangxujia
	 * @date 2015年10月20日下午6:08:32
	 */
	@RequestMapping("rechargeFromWeixin.do")
	@ResponseBody
	public Map<String, Object> rechargeFromWeixin(String taskIdStr,String userIdStr ,String token,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTool.isEmpty(taskIdStr)||StringTool.isEmpty(userIdStr)||StringTool.isEmpty(token)) {
			map.put("code", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//2.校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("code", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//do something here
		map.put("code", Codes.SUCCESS);
		return map;
	}
	
	/**
	 * 模拟支付宝返回报文
	 * @author yangxujia
	 * @date 2015年10月21日下午2:11:56
	 */
	@RequestMapping("/test.do")
	@ResponseBody
	public Map<String, Object> test(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		AlipayResult alipayResult = new AlipayResult();
		alipayResult.setBody("testbody");
		alipayResult.setNotify_id("1");
		alipayResult.setNotify_time(new Date());
		alipayResult.setNotify_type("2");
		alipayResult.setSign_type("RSA");
		alipayResult.setSign("MIICXQIBAAKBgQC8/xF7xxPO06gu0tQRUYGHuTaqmFEjfOdIdcmYzLZrBN7WVh7bJaiFFtZgPtxwBsbkZqOG1n3VXZsE1R7V3II35cqGq/HxY6mcwtP/RkkTzf/31L+okwwwQ4zE8Ly/gSb7jT0IvUPDuoSFFDKjeRCynTgWPToMIIpkTnyE4d0EpwIDAQABAoGBAI+vl/ozuwJ2dhnLHYFhJwoprL77+EvzrN6w9iieEl3AoS3w4ZqYwcsVNryLbgoZjOKeidp8fcRWWwHMiZnsAG7yVbZAwqMT4Wi4TgDapiRnWNV5eAcAmLYdjyMslmLsfYcYcU16+4Ff9+ACHuoIUdX2YqCKsAzldrHL28q1ixYpAkEA4FXXgYxrz9q+RIq/MoFPtpb5F5v6UdiLOcKAoXGS2OQxCBkWEMr587wtBaVaWILIKaU0CI6QlOfyXiODLenC5QJBANesSNMoyVmPuGBGzxb0pA8KBTddQSbMkrWsxWM3QxsSa0r4pTo5RUaYAYXgdrMq2VNPMOmy0Q80ebmmnOtvtJsCQQCyAStJLkT13xoOhRGFX7oCpfTs2OVdQDfpEv7CG75rv1VwmYc6t/RxhCUtT7FKfiuZb+7Dw2vR2+Ii9IbITa5JAkBxdyttJEUiQFGBHV+wNQ7m9p+d7ArtzazVtcq3EwjUAlG+RKgwxJakuC388AWi50Uk5kHWgYQpWrM7r+NYnhFHAkB7/G+F2ITz4gEUw6dLdT/I8tY9r70cpupyteg+fhVgFmshT1oxMvAVg/oHuvEtuY78vDZ4BuEP4rpxdcSvXIkE");
		paymentservice.save(alipayResult);
		//do something here
		map.put("code", Codes.SUCCESS);
		return map;
	}
}
