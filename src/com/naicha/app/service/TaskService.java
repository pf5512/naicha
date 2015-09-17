package com.naicha.app.service;

import java.util.List;

import com.naicha.app.mode.Task;


public interface TaskService {
	public Task save(Task task);
	public List<Task>findByTime();
	public List<Task>findByTimeSlipeUp(String servicesTime);
}
