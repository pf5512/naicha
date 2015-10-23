package com.naicha.app.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.ApplyDao;
import com.naicha.app.dao.TaskDao;
import com.naicha.app.dao.UserDao;
import com.naicha.app.mode.Task;
import com.naicha.app.mode.User;
import com.naicha.app.service.TaskService;
import com.naicha.app.utils.CalcDate;
import com.naicha.web.vo.UserMongo;
import com.test.MonDB;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ApplyDao applyDao;
	
	@Override
	public Task save(Task task) {
		return taskDao.save(task);
	}

	/**
	 * 查找附近的任务
	 */
	@Override
	public List<Task> findTaskNearBy(String jinwei, int userId,int start,int end) {
		List<Task> taskList1 = MonDB.getTaskList(jinwei, start, end);
		List<Integer> taskIdlist = new ArrayList<Integer>();
		for (Task task : taskList1) {
			taskIdlist.add(task.getId());
		}
		List<Object[]> objList =  taskDao.findTaskNearBy(userId,taskIdlist);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}
	
	@Override
	public List<Task> findByTime(Integer userId) {
		
		List<Object[]> objList = taskDao.findByTime(userId);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}

	@Override
	public List<Task> findByTimeSlipeUp(String servicesTime,Integer userId) {
		
		List<Object[]> objList = taskDao.findByTimeSlipeUp(servicesTime,userId);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}

	private Task convert(Object[] obj){
		Task task = new Task();
		//t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,tu.counts as signupCount , c.counts as collectionCount
//		    0       1      2       3        4            5           6        7       8       9            10                               11                                    
		task.setId((Integer) obj[0]);
		task.setUserId((Integer) obj[1]);
		task.setTaskType((Integer) obj[2]);
		task.setReward((Integer) obj[3]);
		task.setServicesTime((Date) obj[4]);
		task.setTimeLength((Integer) obj[5]);
		task.setPublicTime((Date) obj[6]);
		task.setNotes((String) obj[7]);
		task.setLocation((String) obj[8]);
		task.setHeadPicture((String) obj[9]);
		task.setSignupCount((BigInteger) obj[10]);
		task.setIsCollect((BigInteger) obj[11]);
		
		//时间的另外一种展示方法
		String relativeToCurrentTime = getDayString((Date) obj[4]);
		task.setRelativeToCurrentTime(relativeToCurrentTime);
		//距离报名截止时间
		String toClosingTime = calcDateTime((Date) obj[4]);
		task.setRelativeToClosingTime(toClosingTime);
		return task;
	}
	@SuppressWarnings("deprecation")
	private  String getDayString(Date startDate){
		int month = startDate.getMonth();
		int date = startDate.getDate();
		int hour = startDate.getHours();
		int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
		long today = (System.currentTimeMillis()+offSet)/86400000;
		long start = (startDate.getTime()+offSet)/86400000;
		switch ((int)(start-today)) {
		case -2:
			return  "前天  "+hour+"点" ;
		case -1:
			return "昨天  "+hour+"点";
		case 0:
			return  "今天  "+hour+"点";
		case 1:
			return  "明天  "+hour+"点";
		case 2:
			return "后天  "+hour+"点";
		default:
			 return month+"月"+date+"日  "+hour+"点";
		}
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

	/**
	 * 任务详情
	 */
	@Override
	public Task findDetail(Integer id) {
		Object[] obj = taskDao.findDetail(id);
		if(obj==null){
			return null;
		}else{
			Task task = convert2(obj);
			return task;
		}
	}
	
	private Task convert2(Object[] obj){
		Task task = new Task();
//t.id,taskType,reward,servicesTime,timeLength, notes,t.userId,u.headPicture,u.name,u.sex,u.birthday,tu.counts as signupCount
//	0     1       2      3            4           5      6         7           8      9       10                        11               	
		task.setId((Integer) obj[0]);
		task.setTaskType((Integer) obj[1]);
		task.setReward((Integer) obj[2]);
		task.setServicesTime((Date) obj[3]);
		task.setTimeLength((Integer) obj[4]);
		task.setNotes((String) obj[5]);
		task.setUserId((Integer) obj[6]);
		task.setHeadPicture((String) obj[7]);
		task.setName((String) obj[8]);
		task.setSex((Integer) obj[9]);
		task.setAge(getAge((Date) obj[10]));
		task.setSignupCount((BigInteger) obj[11]);
		//时间的另外一种展示方法
		String relativeToCurrentTime = getDayString((Date) obj[3]);
		task.setRelativeToCurrentTime(relativeToCurrentTime);
		//距离报名截止时间
		String toClosingTime = calcDateTime((Date) obj[3]);
		task.setRelativeToClosingTime(toClosingTime);
		return task;
	}
	private Integer getAge(Date mydate){
		  Date date=new Date();     
		  long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
		  Integer year=(int) (day/365);
		return year;
		}

	//查找附近的奶茶（英语老师）先查找20条出来
	@Override
	public List<User> findTA(String jinwei) {
		//通过mongodb查找相关
		List<UserMongo> userMongoList =  new ArrayList<UserMongo>();
		Integer userType = 0;//奶茶为0
		Integer start = 0;
		Integer end =20;
		userMongoList = MonDB.getUserDistanceList(jinwei, userType, start, end);
		List<Integer> userIdlist = new ArrayList<Integer>();
		for (UserMongo userMongo : userMongoList) {
			userIdlist.add(userMongo.get_id());
		}
		List<Object[]> objList = userDao.findTA(userIdlist);
		List<User> userList = new ArrayList<User>();

		userList = convert3(objList);
		return userList;
	}

	//查找附近的奶茶（英语老师）先查找20条出来
	@Override
	public List<User> findTABySex(String jinwei,String sex) {
		//通过mongodb查找相关
		List<UserMongo> userMongoList =  new ArrayList<UserMongo>();
		Integer userType = 0;//奶茶为0
		Integer start = 0;
		Integer end =20;
		userMongoList = MonDB.getUserDistanceListBySex(jinwei, userType, Integer.parseInt(sex), start, end);
		List<Integer> userIdlist = new ArrayList<Integer>();
		for (UserMongo userMongo : userMongoList) {
			userIdlist.add(userMongo.get_id());
		}
		List<Object[]> objList = userDao.findTA(userIdlist);
		List<User> userList = new ArrayList<User>();

		userList = convert3(objList);
		
		return userList;
	}
	
	private List<User> convert3(List<Object[]> objList) {
		List<User> userList = new ArrayList<User>();
		for (Object[] obj : objList) {
			//id,headPicture,name,sex,birthday,rank,address,profession 
			// 0   1          2    3    4       5     6       7
			User user =  new User();
			user.setId((Integer) obj[0]);
			user.setHeadPicture((String) obj[1]);
			user.setName((String) obj[2]);
			user.setSex((Integer) obj[3]);
			user.setAge(getAge((Date)obj[4]));
			user.setRank((String) obj[5]);
			user.setAddress((String) obj[6]);
			user.setProfession((String) obj[7]);
			user.setServiceType((String) obj[8]);
			userList.add(user);
		}
		return userList;
	}

	public List<User> findTAByRank(String jinwei,Integer page) {
		//通过mongodb查找相关
		List<UserMongo> userMongoList =  new ArrayList<UserMongo>();
		Integer userType = 0;//奶茶为0
		Integer pageSize = 2;
		Integer a = (page-1)*pageSize;
		List<Object[]> objList = userDao.findTAByRank(a,pageSize);
		List<User> userList = new ArrayList<User>();

		userList = convert3(objList);
		return userList;
	}
	@Override
	public List<Task> findByTimeByBoy(int userId) {
		List<Object[]> objList = taskDao.findByTimeByBoy(userId);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}

	@Override
	public List<Task> findByTimeByBoySlipeUp(String servicesTime,Integer userId) {
		
		List<Object[]> objList = taskDao.findByTimeByBoySlipeUp(servicesTime,userId);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}
	
	@Override
	public List<Task> findByTimeByGirl(int userId) {
		List<Object[]> objList = taskDao.findByTimeByGirl(userId);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}
	
	@Override
	public List<Task> findByTimeByGirlSlipeUp(String servicesTime,Integer userId) {
		
		List<Object[]> objList = taskDao.findByTimeByGirlSlipeUp(servicesTime,userId);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task  = convert(obj);
			taskList.add(task);
		}
		return taskList;
	}

	/**
	 * 根据用户id获得任务
	 * @author yangxujia
	 * @date 2015年9月25日下午4:53:14
	 */
	@Override
	public List<Task> findTaskByUserId(String userIdStr) {
		List<Object[]> objList = taskDao.findTaskByUserId(userIdStr);
		List<Task> taskList = new ArrayList<Task>();
		for (Object[] obj : objList) {
			Task task =  new Task();
			// t.id, t.userId, servicesTime, taskType, timeLength, reward, tu.counts AS signupCount, status 
			//   0     1         2             3           4          5         6                      7
			task.setId((Integer) obj[0]);
			task.setUserId((Integer) obj[1]);
			task.setRelativeToCurrentTime(CalcDate.getDayString((Date) obj[2]));
			task.setTaskType((Integer) obj[3]);
			task.setTimeLength((Integer) obj[4]);
			task.setReward((Integer) obj[5]);
			task.setSignupCount((BigInteger) obj[6]);
			task.setStatus((Integer) obj[7]);
			taskList.add(task);
		}
		return taskList;
	}

	@Override
	public List<Task> findByTimeType(String timeType, String currentPage,
			String pageSize) {
		//分页处理
		Integer start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(pageSize);
		Integer size = Integer.parseInt(pageSize);
		List<Object[]> objList = taskDao.findByTimeType(timeType,start,size);
		List<Task> taskList = new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  convert3(objects);
			taskList.add(task);
		}
		return taskList;
	}
	
	private Task convert3(Object[] obj) {
		Task task =  new Task();
		task.setPublicTime((Date) obj[0]);//发布时间
		task.setId((Integer) obj[1]);//任务编号
		task.setName((String) obj[2]);//雇主名称
		task.setServicesTime((Date) obj[3]);//服务时间
		task.setTimeLength((Integer) obj[4]);//时长
		task.setNotes((String) obj[5]);//备注
		task.setStatus((Integer) obj[6]);//状态
		task.setSignupCount((BigInteger) obj[7]);//报名人数
		task.setReward((Integer) obj[8]);//赏金
		task.setTotop((Integer) obj[9]);
		return task;
	}

	@Override
	public BigInteger findByTimeTypeCount(String timeType) {
		return taskDao.findByTimeTypeCount(timeType);
	}

	@Override
	public Integer toTop(String totop,String id) {
		return taskDao.toTop(totop,id);
	}

	@Override
	public List<Task> findByTaskId(String taskId) {
		List<Object[]> objList = taskDao.findByTaskId(taskId);
		List<Task> taskList = new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  convert3(objects);
			taskList.add(task);
		}
		return taskList;
	}

	@Override
	public List<Task> findByName(String name) {
		List<Object[]> objList = taskDao.findByName("%"+name+"%");
		List<Task> taskList = new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  convert3(objects);
			taskList.add(task);
		}
		return taskList;
	}

	/**
	 * 查找已中标的任务
	 */
	@Override
	public List<Task> alreadyZhongbiao(String userIdStr) {
		List<Object[]> objList = applyDao.alreadyZhongbiao(userIdStr);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  new Task();
			//u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime
			//0                   1      2     3         4        5            6
			task.setHeadPicture((String) objects[0]);
			task.setUserId((Integer) objects[1]);
			task.setId((Integer) objects[2]);
			task.setTaskType((Integer) objects[3]);
			task.setReward((Integer) objects[4]);
			task.setTimeLength((Integer) objects[5]);
			task.setRelativeToCurrentTime(CalcDate.getDayString((Date) objects[6]));
			taskList.add(task);
		}
		return taskList;
	}
	
	//已完成
	@Override
	public List<Task> alreadyFinish(String userIdStr) {
		List<Object[]> objList = applyDao.alreadyFinish(userIdStr);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  new Task();
			//u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime
			//0                   1      2     3         4        5            6
			task.setHeadPicture((String) objects[0]);
			task.setUserId((Integer) objects[1]);
			task.setId((Integer) objects[2]);
			task.setTaskType((Integer) objects[3]);
			task.setReward((Integer) objects[4]);
			task.setTimeLength((Integer) objects[5]);
			task.setRelativeToCurrentTime(CalcDate.getDayString((Date) objects[6]));
			taskList.add(task);
		}
		return taskList;
	}

	//已报名
	@Override
	public List<Task> alreadySignUp(String userIdStr) {
		List<Object[]> objList = applyDao.alreadySignUp(userIdStr);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  new Task();
			//u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime
			//0                   1      2     3         4        5            6
			task.setHeadPicture((String) objects[0]);
			task.setUserId((Integer) objects[1]);
			task.setId((Integer) objects[2]);
			task.setTaskType((Integer) objects[3]);
			task.setReward((Integer) objects[4]);
			task.setTimeLength((Integer) objects[5]);
			task.setRelativeToCurrentTime(CalcDate.getDayString((Date) objects[6]));
			task.setSignupCount((BigInteger) objects[7]);
			taskList.add(task);
		}
		return taskList;
	}
	
	//已收藏
	@Override
	public List<Task> alreadyCollected(String userIdStr) {
		List<Object[]> objList = applyDao.alreadyCollected(userIdStr);
		List<Task> taskList =  new ArrayList<Task>();
		for (Object[] objects : objList) {
			Task task =  new Task();
			//u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime
			//0                   1      2     3         4        5            6
			task.setHeadPicture((String) objects[0]);
			task.setUserId((Integer) objects[1]);
			task.setId((Integer) objects[2]);
			task.setTaskType((Integer) objects[3]);
			task.setReward((Integer) objects[4]);
			task.setTimeLength((Integer) objects[5]);
			task.setRelativeToCurrentTime(CalcDate.getDayString((Date) objects[6]));
			task.setSignupCount((BigInteger) objects[7]);
			taskList.add(task);
		}
		return taskList;
	}


}
