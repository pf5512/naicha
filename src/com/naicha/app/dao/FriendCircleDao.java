package com.naicha.app.dao;

import org.springframework.data.repository.Repository;

import com.naicha.app.mode.FriendCircle;

public interface FriendCircleDao extends Repository<FriendCircle, Integer> {

	/**
	 * 朋友圈保存
	 * @author yangxujia
	 * @date 2015年9月12日下午4:34:47
	 */
	public FriendCircle save(FriendCircle friendCircle);

}
