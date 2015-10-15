package com.naicha.app.service;

import java.math.BigInteger;
import java.util.List;

import com.naicha.app.mode.Task;
import com.naicha.app.mode.User;


public interface TaskService {
	public Task save(Task task);
	public List<Task>findByTime(Integer userId);
	public List<Task>findByTimeSlipeUp(String servicesTime,Integer userId);
	public Task findDetail(Integer id);
	public List<User> findTA(String jinwei);
	public List<Task> findByTimeByBoy(int parseInt);
	public List<Task> findByTimeByGirl(int parseInt);
	public List<Task> findByTimeByBoySlipeUp(String servicesTime, Integer userId);
	public List<Task> findByTimeByGirlSlipeUp(String servicesTime, Integer userId);
	public List<User> findTABySex(String jinwei, String sex);
	public List<User> findTAByRank(String jinwei, Integer page);
	public List<Task> findTaskByUserId(String userIdStr);
	public List<Task> findByTimeType(String timeType, String currentPage,
			String pageSize);
	public BigInteger findByTimeTypeCount(String timeType);
	public Integer toTop(String totop, String id);
	public List<Task> findByTaskId(String taskId);
	public List<Task> findByName(String name);
}
