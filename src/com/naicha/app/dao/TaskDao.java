package com.naicha.app.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Task;

public interface TaskDao extends Repository<Task, Integer> {
	public Task save(Task task);
	/**
	 * 按截止时间查找全部
	 * @author yangxujia
	 * @date 2015年9月22日上午10:01:55
	 */
	 @Query(nativeQuery=true,value="select t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,(case when tu.counts is null then 0 else tu.counts end) signupCount ,  (case when c.counts is null then 0 else c.counts end) isCollected from "
	 		+ "task t  left join user u on t.userId=u.id  			"
	 		+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id 		 "
	 		+ "LEFT JOIN (select taskId,  count(userId) counts from collection WHERE userId=?1 GROUP BY taskId) c on c.taskId=t.id where servicesTime >= now() and  t.toTop > 0 	 "
	 		+ "order by servicesTime asc;")
	public List<Object[]>findByTime(Integer userId);
	
	/**
	 * 按截止时间查找全部
	 * 上拉获取更多
	 * @author yangxujia
	 * @date 2015年9月17日下午3:07:15
	 */
	@Query(nativeQuery=true,value="select t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,(case when tu.counts is null then 0 else tu.counts end) signupCount ,  (case when c.counts is null then 0 else c.counts end) isCollected from "
	 		+ "task t  left join user u on t.userId=u.id  			"
	 		+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id 		 "
	 		+ "LEFT JOIN (select taskId,  count(userId) counts from collection WHERE userId=?2  GROUP BY taskId) c on c.taskId=t.id where servicesTime > ?1 	 "
	 		+ "order by servicesTime asc;")
	public List<Object[]>findByTimeSlipeUp(String servicesTime,Integer userId);
	
	/**
	 * 单条详情
	 * @author yangxujia
	 * @date 2015年9月22日上午10:04:41
	 */
	@Query(nativeQuery=true,value="select t.id,taskType,reward,servicesTime,timeLength, notes,t.userId,u.headPicture,u.name,u.sex,u.birthday,tu.counts as signupCount from task t "
			+ "left join user u on t.userId=u.id  "
			+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id "
			+ "where t.id = ?1 ")
	public Object[] findDetail(Integer id);
	
	/**
	 * 按截止时间查找男生
	 * @author yangxujia
	 * @date 2015年9月22日上午10:04:55
	 */
	 @Query(nativeQuery=true,value="select t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,(case when tu.counts is null then 0 else tu.counts end) signupCount ,  (case when c.counts is null then 0 else c.counts end) isCollected from "
		 		+ "task t  left join user u on t.userId=u.id  			"
		 		+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id 		 "
		 		+ "LEFT JOIN (select taskId,  count(userId) counts from collection WHERE userId=?1 GROUP BY taskId) c on c.taskId=t.id where servicesTime >= now()  and u.sex=1	 "
		 		+ "order by servicesTime asc;")
	public List<Object[]> findByTimeByBoy(Integer userId);

	/**
	 * 按截止时间查找男生
	 * 上拉查找更多
	 * @author yangxujia
	 * @date 2015年9月22日上午10:04:55
	 */
	@Query(nativeQuery=true,value="select t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,(case when tu.counts is null then 0 else tu.counts end) signupCount ,  (case when c.counts is null then 0 else c.counts end) isCollected from "
	 		+ "task t  left join user u on t.userId=u.id  			"
	 		+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id 		 "
	 		+ "LEFT JOIN (select taskId,  count(userId) counts from collection WHERE userId=?2  GROUP BY taskId) c on c.taskId=t.id where servicesTime > ?1 and sex=1	 "
	 		+ "order by servicesTime asc;")
	public List<Object[]>findByTimeByBoySlipeUp(String servicesTime,Integer userId);
	/**
	 * 按截止时间查找女生
	 * @author yangxujia
	 * @date 2015年9月22日上午10:05:33
	 */
	@Query(nativeQuery=true,value="select t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,(case when tu.counts is null then 0 else tu.counts end) signupCount ,  (case when c.counts is null then 0 else c.counts end) isCollected from "
	 		+ "task t  left join user u on t.userId=u.id  			"
	 		+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id 		 "
	 		+ "LEFT JOIN (select taskId,  count(userId) counts from collection WHERE userId=?1 GROUP BY taskId) c on c.taskId=t.id where servicesTime >= now()  and u.sex=0	 "
	 		+ "order by servicesTime asc;")
	public List<Object[]> findByTimeByGirl(int userId);
	
	/**
	 * 按截止时间查找女生sex=0为女生
	 * 上拉查找更多
	 * @author yangxujia
	 * @date 2015年9月22日上午10:04:55
	 */
	@Query(nativeQuery=true,value="select t.id,t.userId,taskType,reward,servicesTime,timeLength, publicTime,notes,location,headPicture,(case when tu.counts is null then 0 else tu.counts end) signupCount ,  (case when c.counts is null then 0 else c.counts end) isCollected from "
	 		+ "task t  left join user u on t.userId=u.id  			"
	 		+ "left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id 		 "
	 		+ "LEFT JOIN (select taskId,  count(userId) counts from collection WHERE userId=?2  GROUP BY taskId) c on c.taskId=t.id where servicesTime > ?1 and u.sex=0 "
	 		+ "order by servicesTime asc;")
	public List<Object[]>findByTimeByGirlSlipeUp(String servicesTime,Integer userId);
	
	@Query(nativeQuery=true,value="SELECT t.id, t.userId, servicesTime, taskType, timeLength, reward, tu.counts AS signupCount, status "
			+ "FROM task t LEFT JOIN ( SELECT taskId, count(userId) counts FROM apply GROUP BY taskId ) tu ON tu.taskId = t.id "
			+ "WHERE userId = ?1 ORDER BY servicesTime ASC ")
	public List<Object[]> findTaskByUserId(String userIdStr);
	
	//管理后台
	@Query(nativeQuery=true,value="select t.publicTime, t.id, u.name, t.servicesTime , t.timeLength , t.notes,t.status , case when tu.counts is null then 0 ELSE tu.counts end as signupCount ,t.reward ,t.toTop " 
			+ "from 	task t  left join user u on t.userId=u.id  left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id " 
			+ "where  DATE_SUB(CURDATE(), INTERVAL ?1 DAY) < date(publicTime)	  "
			+ "order by toTop desc, servicesTime asc limit ?2,?3  ")
	public List<Object[]> findByTimeType(String timeType, Integer start, Integer size);
	
	//计算总条数
	@Query(nativeQuery=true,value="select count(1) " 
			+ "from 	task t  left join user u on t.userId=u.id  left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id " 
			+ "where  DATE_SUB(CURDATE(), INTERVAL ?1 DAY) < date(publicTime) ")
	public BigInteger findByTimeTypeCount(String timeType);
	
	@Modifying
	@Query(nativeQuery=true,value="update task set toTop = ?1 where id=?2 " )
	public Integer toTop(String toTop,String id);
	
	/**
	 * 根据id查找单条数据
	 * @author yangxujia
	 * @date 2015年10月15日上午11:12:37
	 */
	@Query(nativeQuery=true,value="select t.publicTime, t.id, u.name, t.servicesTime , t.timeLength , t.notes,t.status , case when tu.counts is null then 0 ELSE tu.counts end as signupCount ,t.reward ,t.toTop "
			+ "from 	task t  left join user u on t.userId=u.id  left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id  WHERE t.id = ?1 " )
	public List<Object[]> findByTaskId(String taskId);
	
	/**
	 * 根据名字查找
	 * @author yangxujia
	 * @date 2015年10月15日上午11:12:37
	 */
	@Query(nativeQuery=true,value="select t.publicTime, t.id, u.name, t.servicesTime , t.timeLength , t.notes,t.status , case when tu.counts is null then 0 ELSE tu.counts end as signupCount ,t.reward ,t.toTop "
			+ "from task t  left join user u on t.userId=u.id  left join (select taskId , count(userId) counts from apply GROUP BY taskId) tu on tu.taskId=t.id  WHERE u.name like ?1 " )
	public List<Object[]> findByName(String name);
}
