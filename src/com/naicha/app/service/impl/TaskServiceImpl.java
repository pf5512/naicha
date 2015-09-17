package com.naicha.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.TaskDao;
import com.naicha.app.mode.Task;
import com.naicha.app.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	@Override
	public Task save(Task task) {
		return taskDao.save(task);
	}

	@Override
	public List<Task> findByTime() {
		List<Object[]> objList = taskDao.findByTime();
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}

	@Override
	public List<Task> findByTimeSlipeUp(String servicesTime) {
		List<Object[]> objList = taskDao.findByTimeSlipeUp(servicesTime);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}

	private Task convert(Object[] obj){
		Task task = new Task();
		task.setId((Integer) obj[0]);
		task.setUserId((Integer) obj[1]);
		task.setTaskType((Integer) obj[2]);
		task.setReward((Integer) obj[3]);
		task.setServicesTime((Date) obj[4]);
		task.setTimeLength((Integer) obj[5]);
		task.setPublicTime((Date) obj[6]);
		task.setNotes((String) obj[7]);
		task.setLocation((String) obj[8]);
		//时间的另外一种展示方法
		String relativeToCurrentTime = calcTomorrow((Date) obj[4]);
		task.setRelativeToCurrentTime(relativeToCurrentTime);
		//距离报名截止时间
		String toClosingTime = calcDateTime((Date) obj[4]);
		task.setRelativeToCurrentTime(toClosingTime);
		return task;
	}

	private String calcTomorrow(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		
		return sdf.format(date);
		
	}

	private String calcDateTime(Date d) {
		Long t = d.getTime();
		Long now = new Date().getTime();
		long ss = (t-now) / (1000); // 共计秒数
		int hh = (int) ss / 3600; // 共计小时数
		int day =hh/24;
		int hour =hh%24;
		if (hh > 24) {
			return day + "天"+hour+"小时";
		} else {
			return hh + "小时";
		} 
	}
	public static void main(String[] args) {
		TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
		System.out.println(taskServiceImpl.calcTomorrow(new Date()));
	}
}
