package com.naicha.app.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.AlipayResultDao;
import com.naicha.app.dao.ApplyDao;
import com.naicha.app.mode.AlipayResult;
import com.naicha.app.mode.Apply;
import com.naicha.app.service.ApplyService;
import com.naicha.app.service.PaymentService;
import com.naicha.app.service.PushService;
import com.sun.istack.internal.FinalArrayList;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private AlipayResultDao alipayResultDao;
	@Override
	public AlipayResult save(AlipayResult alipayResult) {
		return alipayResultDao.save(alipayResult);
	}

	//接收支付宝传进来的参数
	public void saveAndRecord(Map<String, String> params){
		AlipayResult alipayResult = new AlipayResult();
		//保存支付宝的参数
		alipayResult.setNotify_id(params.get("notify_id"));
		alipayResult.setNotify_time(new Date(params.get("notify_time")));
		alipayResult.setNotify_type(params.get("notify_type"));
		alipayResult.setSign(params.get("sign"));
		alipayResult.setTotal_fee(Double.parseDouble(params.get("total_fee")));
		alipayResultDao.save(alipayResult);
		
	}
	
}
