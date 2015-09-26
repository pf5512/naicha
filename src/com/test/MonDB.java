package com.test;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.naicha.web.vo.UserMongo;

public class MonDB {
	/**
	 * 获取距离排序，最核心的一步，总算搞定
	 * @author yangxujia
	 * @date 2015年9月22日下午2:56:09
	 */
	public static List<UserMongo> getUserDistanceList(String jinwei, Integer userType,Integer start,Integer end) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("127.0.0.1", 27017);
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
	
	public static List<UserMongo> getUserDistanceListBySex(String jinwei, Integer userType,Integer sexIn,Integer start,Integer end) {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("127.0.0.1", 27017);
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
			mongoClient = new MongoClient("127.0.0.1", 27017);
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
			  mongoClient = new MongoClient();
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
			  mongoClient = new MongoClient();
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
	public static void updateLocation(String _id,String jinwei){
		MongoClient  mongoClient = null;
		try {
			mongoClient = new MongoClient();
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
			mongoClient = new MongoClient();
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
			mongoClient = new MongoClient();
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
			mongoClient = new MongoClient("127.0.0.1", 27017); 
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
			mongoClient = new MongoClient();
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
	public static void main(String[] args) {
//		Integer _id = 6;
//		Integer userType = 0;
//		String jinwei = "22.5361069536,114.0088021958";
//		setUserLocation(jinwei,_id);
//		updateUserType(_id,userType); 
		
//		delete();
		
//		String _id = "N";
//		Integer userType = 1;
//		updateUserType(_id,userType); 
		
//		String jinwei = "6,6";
//		updateLocation(_id, jinwei); 
		
//		Integer _id = 6;
//		Integer sex =0;
//		updateSex(_id, sex);
		
//		Integer _id = 6;
//		String rank = "L1";
//		updateRank(_id, rank);

		Integer _id = 5;
		Integer isActivate = 1;
		updateActive(_id, isActivate);
		
		String jinweiString="22.5438700000,113.9503390000";
		List<UserMongo> userList;
		userList=  getUserDistanceListBySex(jinweiString,0,0,0, 10);
	    for (UserMongo userMongo : userList) {
			System.out.println(userMongo.get_id());
			System.out.println(userMongo.getDistance());
			System.out.println(userMongo.getSex());
			System.out.println(userMongo.getRank());
		}
	    userList=getUserDistanceList(jinweiString,1,0,9);
	    for (UserMongo userMongo : userList) {
			System.out.println(userMongo.get_id());
			System.out.println(userMongo.getDistance());
			System.out.println(userMongo.getSex());
			System.out.println(userMongo.getRank());
		}
	}
}
