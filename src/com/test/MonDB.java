package com.test;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.naicha.app.mode.Task;
import com.naicha.app.utils.Codes;
import com.naicha.app.utils.StringTools;
import com.naicha.web.vo.UserMongo;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class MonDB {
	
	protected static final Logger LOG = LoggerFactory.getLogger(MonDB.class);
	private static String host=Codes.IP;
	/**
	 * 用户
	 * 获取距离排序，最核心的一步，总算搞定
	 * @author yangxujia
	 * @date 2015年9月22日下午2:56:09
	 */
	public static List<UserMongo> getUserDistanceList(String jinwei, Integer userType,Integer start,Integer end) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("test");
		DBCollection collection = db.getCollection("location");
		BasicDBObject queryObject = new BasicDBObject();
		String jsonString = "{position:{$near:["+jinwei+"]},userType:"+userType+",isActivate :1}";
		queryObject = (BasicDBObject) JSON.parse(jsonString);
		DBCursor querycursor = collection.find(queryObject).skip(start)
				.limit(end);
		List<UserMongo>   userMongoList =  new ArrayList<UserMongo>();
		while (querycursor.hasNext()) {
			BasicDBObject  resultObj= (BasicDBObject)querycursor.next();
//			System.out.println(resultObj);
			Integer _id = (Integer)resultObj.get("_id");
			BasicDBList positionList =(BasicDBList)resultObj.get("position");
			Double longitude=(Double)positionList.get(0);
			Double latitude=(Double)positionList.get(1);
			Integer sex = (Integer)resultObj.get("sex");
			String rank = (String)resultObj.get("rank");
			UserMongo userMongo = new UserMongo();
			userMongo.set_id(_id);
			Double distance = getDistanc(jinwei, longitude,latitude);
			userMongo.setDistance(distance);
			userMongo.setSex(sex);
			userMongo.setRank(rank);
			userMongoList.add(userMongo);
		}
		return userMongoList;
	}
	
	/**
	 * 获取附近的任务
	 * @author yangxujia
	 * @date 2015年10月22日下午3:33:53
	 */
	public static List<Task> getTaskList(String jinwei,Integer start,Integer end){
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("test");
		DBCollection collection = db.getCollection("task");
		BasicDBObject queryObject = new BasicDBObject();
		String [] str = jinwei.split(",");
		double lo = Double.parseDouble(str[0]);
		double la = Double.parseDouble(str[1]);
		double [] pos = new double[]{lo,la};
		queryObject.put("location", new BasicDBObject("$near",pos));
		queryObject.put("servicesTime", new BasicDBObject("$gte",new Date()));
//		String jsonString = "{location:{$near:["+jinwei+"]}}";
//		queryObject = (BasicDBObject) JSON.parse(jsonString);
		DBCursor querycursor = collection.find(queryObject).skip(start)
				.limit(end);
		List<Task> taskList =  new ArrayList<Task>();
		while (querycursor.hasNext()) {
			BasicDBObject  resultObj= (BasicDBObject)querycursor.next();
			Integer userId = (Integer)resultObj.get("userId");
			Integer taskType = (Integer)resultObj.get("taskType");
			Integer reward = (Integer)resultObj.get("reward");
			Date servicesTime = (Date)resultObj.get("servicesTime");
			Integer timeLength = (Integer)resultObj.get("timeLength");
			String notes = (String)resultObj.get("notes");
			BasicDBList positionList =(BasicDBList)resultObj.get("location");
			Double longitude=(Double)positionList.get(0);
			Double latitude=(Double)positionList.get(1);
			Integer id = (Integer)resultObj.get("id");
			Double distance = getDistanc(jinwei, longitude,latitude);
			LOG.info("id: "+id+" userId "+userId+" taskType:" + taskType+" reward: "+ reward+" servicesTime:"+servicesTime+"timeLength : "+ timeLength +" location: "+ latitude+","+latitude+" notes:"+notes +"distance: "+ distance);
			Task task = new Task();
			task.setId(id);
			task.setUserId(userId);
			task.setTaskType(taskType);
			task.setReward(reward);
			task.setServicesTime(servicesTime);
			task.setTimeLength(timeLength);
			task.setNotes(notes);
			task.setDistance(distance);
			taskList.add(task);
		}
		return taskList;
	}
	public static List<UserMongo> getUserDistanceListBySex(String jinwei, Integer userType,Integer sexIn,Integer start,Integer end) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("test");
		DBCollection collection = db.getCollection("location");
		BasicDBObject queryObject = new BasicDBObject();
		String jsonString = "{position:{$near:["+jinwei+"]},userType:"+userType+",sex:"+sexIn+",isActivate: 1}";
		queryObject = (BasicDBObject) JSON.parse(jsonString);
		DBCursor querycursor = collection.find(queryObject).skip(start)
				.limit(end);
		List<UserMongo>   userMongoList =  new ArrayList<UserMongo>();
		while (querycursor.hasNext()) {
			BasicDBObject  resultObj= (BasicDBObject)querycursor.next();
//			System.out.println(resultObj);
			Integer _id = (Integer)resultObj.get("_id");
			BasicDBList positionList =(BasicDBList)resultObj.get("position");
			Double longitude=(Double)positionList.get(0);
			Double latitude=(Double)positionList.get(1);
			Integer sex = (Integer)resultObj.get("sex");
			String rank = (String)resultObj.get("rank");
			UserMongo userMongo = new UserMongo();
			userMongo.set_id(_id);
			Double distance = getDistanc(jinwei, longitude,latitude);
			userMongo.setDistance(distance);
			userMongo.setSex(sex);
			userMongo.setRank(rank);
			userMongoList.add(userMongo);
		}
		return userMongoList;
	}
	
	private static Double getDistanc(String jinwei, double longt2,double lat2) {
		double PI = 3.14159265358979323; // 圆周率
		double R = 6371229; // 地球的半径
		String str[]=jinwei.split(",");
		double longt1= Double.parseDouble(str[0]);
		double lat1 = Double.parseDouble(str[1]);
		double x, y, distance;
		x = (longt2 - longt1) * PI * R
				* Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);
		return distance/1000;
	}
	/**
	 * 写入坐标
	 * @author yangxujia
	 * @date 2015年9月22日下午2:51:43
	 */
	public  static void setUserLocation(String jinwei, Integer userId) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("test");
		DBCollection collection = db.getCollection("location");
		BasicDBObject inserObject = new BasicDBObject();
		inserObject.put("_id", userId);
		String [] str = jinwei.split(",");
		double lo = Double.parseDouble(str[0]);
		double la = Double.parseDouble(str[1]);
		double [] pos = new double[]{lo,la};
		inserObject.put("position", pos);
		collection.insert(inserObject);
	}
	
	/**
	 * 删除
	 * @author yangxujia
	 * @date 2015年9月22日下午2:56:54
	 */
	public static void delete (){
		MongoClient  mongoClient = null;
		try {
			  mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DBCollection cllecttion = mongoClient.getDB("test").getCollection("location");
		BasicDBObject deleteobj =  new BasicDBObject();
//		double [] pos = new double[]{0.9,0.9};
		deleteobj.put("_id", 1);
		cllecttion.remove(deleteobj);
	}
	public static void updateUserType(Integer _id,Integer userType){
		MongoClient  mongoClient = null;
		try {
			  mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DBCollection collecttion = mongoClient.getDB("test").getCollection("location");
		BasicDBObject p =  new BasicDBObject();
		p.put("_id", _id);
		BasicDBObject o =  new BasicDBObject();
		o.put("userType", userType);
		collecttion.update(p,new BasicDBObject("$set",o));
	}
	
	
	public static void updateLocation(Integer _id,String jinwei){
		MongoClient  mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DBCollection collecttion = mongoClient.getDB("test").getCollection("location");
		BasicDBObject p =  new BasicDBObject();
		p.put("_id", _id);
		BasicDBObject o =  new BasicDBObject();
		String [] str = jinwei.split(",");
		double lo = Double.parseDouble(str[0]);
		double la = Double.parseDouble(str[1]);
		double [] pos = new double[]{lo,la};
		o.put("position", pos);
		collecttion.update(p,new BasicDBObject("$set",o));
	}
	
	public static void updateSex(Integer _id,Integer sex){
		MongoClient  mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DBCollection collecttion = mongoClient.getDB("test").getCollection("location");
		BasicDBObject p =  new BasicDBObject();
		p.put("_id", _id);
		BasicDBObject o =  new BasicDBObject();
		o.put("sex", sex);
		collecttion.update(p,new BasicDBObject("$set",o));
	}
	
	public static void updateRank(Integer _id,String rank){
		MongoClient  mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DBCollection collecttion = mongoClient.getDB("test").getCollection("location");
		BasicDBObject p =  new BasicDBObject();
		p.put("_id", _id);
		BasicDBObject o =  new BasicDBObject();
		o.put("rank", rank);
		collecttion.update(p,new BasicDBObject("$set",o));
	}
	
	public static void setUsertype( Integer userId, Integer userType) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("test");
		DBCollection collection = db.getCollection("location");
		BasicDBObject inserObject = new BasicDBObject();
		inserObject.put("_id", userId);
		inserObject.put("userType", userType);
		collection.insert(inserObject);
	}
	
	/**
	 * 激活奶茶
	 * @author yangxujia
	 * @date 2015年9月23日下午5:02:33
	 */
	public static void updateActive(Integer _id,Integer isActivate){
		MongoClient  mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DBCollection collecttion = mongoClient.getDB("test").getCollection("location");
		BasicDBObject p =  new BasicDBObject();
		p.put("_id", _id);
		BasicDBObject o =  new BasicDBObject();
		o.put("isActivate", isActivate);
		collecttion.update(p,new BasicDBObject("$set",o));
	}
	
	/**
	 * 插入任务
	 * id , userId,taskType,reward,serviceTime,timeLength,
	 * publicTime,notes,location,status,toTop
	 * @author yangxujia
	 * @date 2015年9月22日下午2:51:43
	 */
	public  static void insertTask(Task task) {
		int id = task.getId();
		int userId =  task.getUserId();
		int taskType =  task.getTaskType();
		int reward = task.getReward();
		Date servicesTime =  task.getServicesTime();
		int timeLength =  task.getTimeLength();
		String location = task.getLocation();
		String notes = task.getNotes();
		LOG.info("id: "+id+" userId "+userId+" taskType:" + taskType+" reward: "+ reward+" servicesTime:"+servicesTime);
		LOG.info("timeLength : "+ timeLength +" location: "+location+" notes:"+notes);
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB db = mongoClient.getDB("test");
		DBCollection collection = db.getCollection("task");//插入用户表
		BasicDBObject inserObject = new BasicDBObject();
		inserObject.put("id", id);
		inserObject.put("userId", userId);
		inserObject.put("taskType", taskType);
		inserObject.put("reward", reward);
		inserObject.put("servicesTime", servicesTime);
		inserObject.put("timeLength", timeLength);
		String [] str = location.split(",");
		double lo = Double.parseDouble(str[0]);
		double la = Double.parseDouble(str[1]);
		double [] loc = new double[]{lo,la};
		inserObject.put("location", loc);
		inserObject.put("notes", notes);
		collection.insert(inserObject);
	}
	
	public static void main(String[] args) {
//		Integer _id = 27;
//		Integer userType = 0;
//		String jinwei = "22.5361069536,114.0088021958";
//		setUserLocation(jinwei,_id);
//		updateUserType(_id,userType); 
		
//		delete();
		
//		String _id = "33";
//		Integer userType = 1;
//		updateUserType(_id,userType); 
		
//		String jinwei = "6,6";
//		updateLocation(_id, jinwei); 
		
//		Integer _id = 33;
//		Integer sex =0;
//		updateSex(_id, sex);
		
//		Integer _id = 6;
//		String rank = "L1";
//		updateRank(_id, rank);

		//奶茶认证，只有认证的才会在findTA中显示
		Integer _id = 37;
		Integer isActivate = 1;
		updateActive(_id, isActivate);
//		
//		String jinweiString="22.5438700000,113.9503390000";
//		List<UserMongo> userList;
//		userList=  getUserDistanceListBySex(jinweiString,0,0,0, 10);
//	    for (UserMongo userMongo : userList) {
//			System.out.println(userMongo.get_id());
//			System.out.println(userMongo.getDistance());
//			System.out.println(userMongo.getSex());
//			System.out.println(userMongo.getRank());
//		}
//	    userList=getUserDistanceList(jinweiString,1,0,9);
//	    for (UserMongo userMongo : userList) {
//			System.out.println(userMongo.get_id());
//			System.out.println(userMongo.getDistance());
//			System.out.println(userMongo.getSex());
//			System.out.println(userMongo.getRank());
//		}
		//3.插入数据
//				Task task = new Task();
//				task.setId(102);
//				task.setUserId(4);
//				task.setTaskType(1);
//				task.setReward(50);
//				task.setServicesTime(StringTools.StringToDatetime("2016-12-12 12:12:12"));
//				task.setTimeLength(4);
//				task.setNotes("tell me how to talk ");
//				task.setLocation("22.623423,112.6234");
//				insertTask(task);
		//4.获取任务数据
//		getTaskList("22.623423,112.6234",0,2);
				
	}//main
}//MonDB
