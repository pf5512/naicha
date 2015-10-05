package com.naicha.app.service;

/**
 * 推送类集合，由于本项目用到很多推送，把他们写在一块
 * @author yangxujia
 * @date 2015年10月5日下午2:20:51
 */
public interface PushService {
	
	/**
	 * 报名时的推送
	 * @author yangxujia
	 * @date 2015年10月5日下午2:20:03
	 */
	Integer pushApplyMessage(int taskId, String tagUserIdStr);
}
