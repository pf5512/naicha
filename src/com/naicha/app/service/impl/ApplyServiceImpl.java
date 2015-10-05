package com.naicha.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.ApplyDao;
import com.naicha.app.mode.Apply;
import com.naicha.app.service.ApplyService;
import com.naicha.app.service.PushService;
import com.sun.istack.internal.FinalArrayList;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyDao applyDao;
	@Autowired
	private PushService pushService;

	@Override
	public Apply save(Apply apply) {
		return applyDao.save(apply);
	}

	/**
	 * 报名的同时进行推送
	 */
	@Override
	public Integer updateState(final int taskId, final int userId) {
		
		new Runnable() {
			public void run() {
				//多线程进行推送
				pushService.pushApplyMessage(taskId, userId+"");
			}
		}.run();
		return applyDao.updateState(taskId, userId);
	}
}
