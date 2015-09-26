package com.test;  
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
  
/**  
* 对基本实体的存储测试  
* @author lhy  
*  
*/  
public class EntityTest {  
      
     public static void main(String[] args) throws Exception{  
    	 MongoClient mongoClient = new MongoClient();
    	 DB db = mongoClient.getDB("test");
    	 DBCollection collection = db.getCollection("location");
    	 DBCursor cursor=collection.find();  
         System.out.println("mongodb中的user表结果!!：");  
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
//         collection.find(new Query(Criteria.where("position").near(point).maxDistance(maxDistance)),Location.class);
// 		 mongoTemplate.find(new Query(Criteria.where("position").near(point).maxDistance(maxDistance)),Location.class);

     }  
      
     /**  
     * 保存实体对象  
     * @throws Exception  
     */  
     
     public static void saveEntity2() throws Exception{  
    	 MongoClient mongoClient =  new MongoClient();
    	 DB db = mongoClient.getDB("test");
    	 DBCollection collection=db.getCollection("user"); 
         BasicDBObject object =  new BasicDBObject();
         object.put("location", "");
         collection.insert(object);
     }
     public static void saveEntity() throws Exception{  
          //第一：实例化mongo对象，连接mongodb服务器  包含所有的数据库  s
           
          //默认构造方法，默认是连接本机，端口号，默认是27017  
//          Mongo mongo =new Mongo("localhost",27017);  
    	 MongoClient mongo =new MongoClient();  
           
          //第二：连接具体的数据库  
          //其中参数是具体数据库的名称，若服务器中不存在，会自动创建  
          DB db=mongo.getDB("myMongo");  
           
          //第三：操作具体的表  
         //在mongodb中没有表的概念，而是指集合  
          //其中参数是数据库中表，若不存在，会自动创建  
          DBCollection collection=db.getCollection("user"); 
          String jsonString ="{\"taskList\":[{\"id\":2,\"userId\":4,\"taskType\":1,\"reward\":45,\"servicesTime\":\"2015-09-20 10:12:00\",\"timeLength\":2,\"publicTime\":\"2015-09-17 14:43:47\",\"notes\":\"I need you\",\"location\":\"\",\"relativeToCurrentTime\":\"明天  10点\",\"relativeToClosingTime\":\"18小时\",\"headPicture\":\"head/AB0434B9A00B96BD5C07AD7A1C601D85.jpg\",\"signupCount\":1,\"isCollect\":1,\"name\":\"\",\"sex\":-1,\"age\":0},{\"id\":3,\"userId\":4,\"taskType\":1,\"reward\":45,\"servicesTime\":\"2015-09-21 10:12:00\",\"timeLength\":2,\"publicTime\":\"2015-09-17 14:44:07\",\"notes\":\"I need you\",\"location\":\"\",\"relativeToCurrentTime\":\"后天  10点\",\"relativeToClosingTime\":\"1天18小时\",\"headPicture\":\"head/AB0434B9A00B96BD5C07AD7A1C601D85.jpg\",\"signupCount\":0,\"isCollect\":0,\"name\":\"\",\"sex\":-1,\"age\":0},{\"id\":6,\"userId\":4,\"taskType\":1,\"reward\":45,\"servicesTime\":\"2015-09-24 10:12:00\",\"timeLength\":2,\"publicTime\":\"2015-09-17 14:44:32\",\"notes\":\"I need you\",\"location\":\"\",\"relativeToCurrentTime\":\"8月24日  10点\",\"relativeToClosingTime\":\"4天18小时\",\"headPicture\":\"head/AB0434B9A00B96BD5C07AD7A1C601D85.jpg\",\"signupCount\":0,\"isCollect\":0,\"name\":\"\",\"sex\":-1,\"age\":0},{\"id\":5,\"userId\":4,\"taskType\":1,\"reward\":45,\"servicesTime\":\"2015-09-25 10:12:00\",\"timeLength\":2,\"publicTime\":\"2015-09-17 14:44:25\",\"notes\":\"I need you\",\"location\":\"\",\"relativeToCurrentTime\":\"8月25日  10点\",\"relativeToClosingTime\":\"5天18小时\",\"headPicture\":\"head/AB0434B9A00B96BD5C07AD7A1C601D85.jpg\",\"signupCount\":0,\"isCollect\":0,\"name\":\"\",\"sex\":-1,\"age\":0},{\"id\":7,\"userId\":4,\"taskType\":1,\"reward\":45,\"servicesTime\":\"2015-10-24 10:12:00\",\"timeLength\":2,\"publicTime\":\"2015-09-17 14:44:39\",\"notes\":\"I need you\",\"location\":\"\",\"relativeToCurrentTime\":\"9月24日  10点\",\"relativeToClosingTime\":\"34天18小时\",\"headPicture\":\"head/AB0434B9A00B96BD5C07AD7A1C601D85.jpg\",\"signupCount\":0,\"isCollect\":0,\"name\":\"\",\"sex\":-1,\"age\":0}],\"codes\":\"1\"}";
          DBObject object =  (DBObject) JSON.parse(jsonString);
          //添加操作  
          //在mongodb中没有行的概念，而是指文档  
          BasicDBObject document=new BasicDBObject();  
           
          document.put("id", 1);  
          document.put("name", "小明");  
//          //然后保存到集合中  
//     //     collection.insert(document);  
           
           
          //当然我也可以保存这样的json串  
/*          {  
               "id":1,  
               "name","小明",  
               "address":  
               {  
               "city":"beijing",  
               "code":"065000"  
               }  
          }*/  
          //实现上述json串思路如下：  
          //第一种：类似xml时，不断添加  
          BasicDBObject addressDocument=new BasicDBObject();  
          addressDocument.put("city", "beijing");  
          addressDocument.put("code", "065000");  
          document.put("address", addressDocument);  
          //然后保存数据库中  
          collection.insert(document);  
           
          //第二种：直接把json存到数据库中  
          String jsonTest="{'id':1,'name':'小明',"+  
                   "'address':{'city':'beijing','code':'065000'}"+  
                    "}";  
         DBObject dbobjct=(DBObject)JSON.parse(jsonTest);  
         collection.insert(dbobjct);      
     }  
      
     /**  
     * 遍历所有的  
     * @throws Exception  
     */  
     public static void selectAll() throws Exception{  
          //第一：实例化mongo对象，连接mongodb服务器  包含所有的数据库  
           
          //默认构造方法，默认是连接本机，端口号，默认是27017  
          //相当于Mongo mongo =new Mongo("localhost",27017)  
          Mongo mongo =new Mongo();  
           
          //第二：连接具体的数据库  
          //其中参数是具体数据库的名称，若服务器中不存在，会自动创建  
          DB db=mongo.getDB("test");  
           
          //第三：操作具体的表  
         //在mongodb中没有表的概念，而是指集合  
          //其中参数是数据库中表，若不存在，会自动创建  
          DBCollection collection=db.getCollection("location");  
           
          //查询操作  
          //查询所有  
          //其中类似access数据库中游标概念  
          DBCursor cursor=collection.find();  
          System.out.println("mongodb中的user表结果如下：");  
          while(cursor.hasNext()){  
               System.out.println(cursor.next());  
          }  
     }  
      
     /**  
     * 根据条件查询  
     * @throws Exception  
     */  
     public static void selectPart() throws Exception{  
          //第一：实例化mongo对象，连接mongodb服务器  包含所有的数据库  
           
          //默认构造方法，默认是连接本机，端口号，默认是27017  
          //相当于Mongo mongo =new Mongo("localhost",27017)  
          Mongo mongo =new Mongo();  
           
          //第二：连接具体的数据库  
          //其中参数是具体数据库的名称，若服务器中不存在，会自动创建  
          DB db=mongo.getDB("myMongo");  
           
          //第三：操作具体的表  
         //在mongodb中没有表的概念，而是指集合  
          //其中参数是数据库中表，若不存在，会自动创建  
          DBCollection collection=db.getCollection("user");  
           
      
          //可以直接put  
          BasicDBObject queryObject=new BasicDBObject();  
          queryObject.put("id", 1);  
          DBCursor querycursor=collection.find(queryObject);  
          System.out.println("条件查询如下：");  
          while(querycursor.hasNext()){  
               System.out.println(querycursor.next());  
          }  
     }  
      
     /**  
     * 更新操作  
     * 更新一条记录  
     * @throws Exception  
     */  
     public static void update()throws Exception{  
          //第一：实例化mongo对象，连接mongodb服务器  包含所有的数据库  
           
          //默认构造方法，默认是连接本机，端口号，默认是27017  
          //相当于Mongo mongo =new Mongo("localhost",27017)  
          Mongo mongo =new Mongo();  
           
          //第二：连接具体的数据库  
          //其中参数是具体数据库的名称，若服务器中不存在，会自动创建  
          DB db=mongo.getDB("myMongo");  
           
          //第三：操作具体的表  
         //在mongodb中没有表的概念，而是指集合  
          //其中参数是数据库中表，若不存在，会自动创建  
          DBCollection collection=db.getCollection("user");  
           
          //更新后的对象  
//          第一种更新方式  
          BasicDBObject newBasicDBObject =new BasicDBObject();  
          newBasicDBObject.put("id", 2);  
          newBasicDBObject.put("name", "小红");  
          collection.update(new BasicDBObject().append("id", 1),newBasicDBObject);  
           
//          第二种更新方式  
//          更新某一个字段  
//          BasicDBObject newBasicDBObject =new BasicDBObject().append("$set",new BasicDBObject().append("name", "小红") );  
//          collection.update(new BasicDBObject().append("id", 1).append("name", "小明"),newBasicDBObject);  
  
           
          DBCursor querycursor1=collection.find();  
          System.out.println("更新后结果如下：");  
          while(querycursor1.hasNext()){  
               System.out.println(querycursor1.next());  
          }  
     }  
      
     /**  
     * 删除文档，其中包括删除全部或删除部分  
     * @throws Exception  
     */  
     public static void delete() throws Exception{  
           
          //第一：实例化mongo对象，连接mongodb服务器  包含所有的数据库  
           
          //默认构造方法，默认是连接本机，端口号，默认是27017  
          //相当于Mongo mongo =new Mongo("localhost",27017)  
          Mongo mongo =new Mongo();  
           
          //第二：连接具体的数据库  
          //其中参数是具体数据库的名称，若服务器中不存在，会自动创建  
          DB db=mongo.getDB("myMongo");  
           
          //第三：操作具体的表  
         //在mongodb中没有表的概念，而是指集合  
          //其中参数是数据库中表，若不存在，会自动创建  
          DBCollection collection=db.getCollection("user");  
          BasicDBObject queryObject1=new BasicDBObject();  
          queryObject1.put("id", 2);  
          queryObject1.put("name","小红");  
           
          //删除某一条记录  
         collection.remove(queryObject1);  
          //删除全部  
          //collection.drop();  
           
          DBCursor cursor1=collection.find();  
          System.out.println("删除后的结果如下：");  
          while(cursor1.hasNext()){  
               System.out.println(cursor1.next());  
          }  
           
      
     }  
      
      
}