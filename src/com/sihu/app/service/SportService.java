package com.sihu.app.service;

import java.util.List;

import com.sihu.app.mode.Sport;

public interface SportService {
	/**
	 * 保存活动消息
	 * @author yangxujia
	 * @date 2015-1-16下午10:08:45
	 * @param sport
	 * @return
	 */
	public Sport save(Sport sport);

	public List<Sport> findAll(String location);

	public List<Sport> findBySportType(String location, String sportType);

	public List<Sport> findByDateTime(String location, String time);

	public Sport findById(String location, String id);
}
