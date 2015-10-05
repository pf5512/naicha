package com.naicha.app.service;

import com.naicha.app.mode.Apply;



public interface ApplyService {
	public Apply save(Apply apply);
	
	/**
	 * 报名
	 * @author yangxujia
	 * @date 2015年10月5日下午12:11:48
	 */
	public Integer updateState( int taskId,int userId);
}
