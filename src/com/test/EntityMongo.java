package com.test;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.naicha.app.utils.Codes;

public class EntityMongo {
	public static void main(String[] args) {
	   	 MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient(Codes.IP,27017);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	 DB db = mongoClient.getDB("test");
	   	 DBCollection collection = db.getCollection("location");
	   	 DBCursor cursor=collection.find();  
	        System.out.println("mongodb中的user表结果如下：");  
	        while(cursor.hasNext()){  
	             System.out.println(cursor.next());  
	        } 
	   	 
	   	 BasicDBObject queryObject=new BasicDBObject();  
	        queryObject.put("id", 1);  
	        DBCursor querycursor=collection.find(queryObject);  
	        System.out.println("条件查询如下：");  
	        while(querycursor.hasNext()){  
	             System.out.println(querycursor.next());  
	        }  
	    
		
	}
}
