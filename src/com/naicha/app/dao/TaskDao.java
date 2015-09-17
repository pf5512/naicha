package com.naicha.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Task;

public interface TaskDao extends Repository<Task, Integer> {
	public Task save(Task task);
	//order by service time;
	@Query(nativeQuery=true,value="select id,userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location "
			+ "from task where servicesTime>=now() order by servicesTime asc;")
	public List<Object[]>findByTime();
	
	/**
	 * 上拉获取更多
	 * @author yangxujia
	 * @date 2015年9月17日下午3:07:15
	 */
	@Query(nativeQuery=true,value="select id,userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location "
			+ "from task where servicesTime>=?1 order by servicesTime asc;")
	public List<Object[]>findByTimeSlipeUp(String servicesTime);
}
