package com.naicha.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naicha.app.mode.Task;
import com.naicha.app.service.TaskService;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.MemCached;
import com.naicha.app.utils.StringTool;
import com.naicha.app.utils.StringTools;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskservice;
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(String taskType,String reward,String servicesTime,String timeLength,String notes,
			String jinwei,String userIdStr,String token,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//1.判断参数是否非空
		if (StringTool.isEmpty(token)||StringTool.isEmpty(userIdStr)
				||StringTool.isEmpty(taskType)||StringTool.isEmpty(reward)||StringTool.isEmpty(servicesTime)
				||StringTool.isEmpty(timeLength)) {
			map.put("msg", "参数不能为空！");
			map.put("codes", Codes.PARAMETER_IS_EMPTY);
			return map;
		}
		//2.校验token
		MemCached cached =  MemCached.getInstance();
		String tokenOld = (String)cached.get(userIdStr);
		if(!token.equals(tokenOld)){
			map.put("msg", "token 过期！");
			map.put("codes", Codes.TOKEN_IS_OVER_DUE);
			return map;
		}
		//3.插入数据
		Task task = new Task();
		task.setUserId(Integer.parseInt(userIdStr));
		task.setTaskType(Integer.parseInt(taskType));
		task.setReward(Integer.parseInt(reward));
		task.setPublicTime(new Date());
		task.setServicesTime(StringTools.StringToDatetime(servicesTime));
		task.setTimeLength(Integer.parseInt(timeLength));
		task.setNotes(notes);
		Task rtnTask = new Task();
		rtnTask=taskservice.save(task);
		if (rtnTask!=null) {
			map.put("rtnTask", rtnTask);
			map.put("codes", Codes.SUCCESS);
			return map;	
		}else{
			map.put("codes", Codes.ERROR);
			return map;	
		}
	}
	
	/**
	 * 任务首页加载，首次加载10条,按时间
	 * @author yangxujia
	 * @date 2015年9月17日上午11:52:01
	 */
	@RequestMapping("/findByTime.do")
	@ResponseBody
	public Map<String, Object> findByTime(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Task> taskList = taskservice.findByTime();
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
	
	/**
	 * 上滑
	 * @author yangxujia
	 * @date 2015年9月17日下午4:51:11
	 */
	@RequestMapping("/findByTimeSlipeUp.do")
	@ResponseBody
	public Map<String, Object> findByTimeSlipeUp(String servicesTime,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Task> taskList = taskservice.findByTimeSlipeUp(servicesTime);
		if (taskList.isEmpty()) {
			map.put("codes", Codes.ERROR);
			return map;	
		}else{
			map.put("taskList", taskList);
			map.put("codes", Codes.SUCCESS);
			return map;
		}
	}
}
