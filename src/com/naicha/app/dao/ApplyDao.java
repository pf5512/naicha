package com.naicha.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Apply;

public interface ApplyDao extends Repository<Apply, Integer>{
	
	public Apply save(Apply apply);
	
	/**
	 * 雇主选中是更新状态，已报名是1，已中标2，已完成3
	 * @author yangxujia
	 * @date 2015年10月5日上午11:54:56
	 */
	@Modifying
	@Query(nativeQuery=true,value=""
			+ "update apply set state=1, updateTime=now() where taskId=?1 and userId=?2 ")
	public Integer updateState( int taskId,int userId);
	
	//已中标
	@Query(nativeQuery=true,value="select u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime from apply a left join   task t on t.id = a.taskId LEFT JOIN user u  on  u.id=t.userId  where a.userId = ?1 and  a.state= 2 and t.servicesTime > NOW() ")
	public List<Object[]> alreadyZhongbiao(String userId);
	
	//已完成
	@Query(nativeQuery=true,value="select u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime from apply a left join   task t on t.id = a.taskId LEFT JOIN user u  on  u.id=t.userId  where a.userId = ?1 and  a.state= 3 and t.servicesTime > NOW() ")
	public List<Object[]> alreadyFinish(String userId);

	//已报名
	@Query(nativeQuery=true,value="select u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime ,(select case when COUNT(userId) IS null then 0 else COUNT(userId) end from apply where taskId=t.id) as signupCount "
			+ "from apply a left join   task t on t.id = a.taskId LEFT JOIN user u  on  u.id=t.userId  where a.userId = ?1  and t.servicesTime > NOW() "
			+ " ")
	public List<Object[]> alreadySignUp(String userIdStr);
	
	//已收藏,为方便，写在这DAO中
	@Query(nativeQuery=true,value="select u.headPicture, t.userId,t.id,t.taskType,t.reward,t.timeLength,t.servicesTime ,(select case when COUNT(userId) IS null then 0 else COUNT(userId) end from apply where taskId=t.id) as signupCount "
			+ "from apply a left join   task t on t.id = a.taskId LEFT JOIN user u  on  u.id=t.userId  where a.userId = ?1  and t.servicesTime > NOW() "
			+ " ")
	public List<Object[]> alreadyCollected(String userIdStr);
	
	
}
