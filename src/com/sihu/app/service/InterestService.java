package com.sihu.app.service;

import java.util.List;

import com.sihu.app.mode.Interest;

public interface InterestService {
	/**
	 * 保存趣闻
	 * @author yangxujia
	 * @date 2015-1-23下午7:28:20
	 * @param interest
	 * @return
	 */
	public Interest save(Interest interest);

	public List<Interest> findAll(String location);

	public Interest findById(String location, String id);
}
