package com.naicha.app.service;

import java.util.List;

import com.naicha.app.mode.FriendCircle;
import com.naicha.web.vo.RespFriendCircle;



public interface FriendCircleService {
	/**
	 * 朋友圈保存
	 * @author yangxujia
	 * @date 2015年9月12日下午4:34:47
	 */
	public FriendCircle save(FriendCircle friendCircle);
	
	/**
	 * 朋友圈查询
	 */
	public List<RespFriendCircle> findNearbyOrderByDistance(String jinwei);

	public List<RespFriendCircle> findNearbyOrderByDistanceMonGo(String jinwei);

	public List<RespFriendCircle> findByUserId(String userIdStr);
}
