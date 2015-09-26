package com.naicha.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.FriendCircle;

public interface FriendCircleDao extends Repository<FriendCircle, Integer> {

	/**
	 * 朋友圈保存
	 * @author yangxujia
	 * @date 2015年9月12日下午4:34:47
	 */
	public FriendCircle save(FriendCircle friendCircle);

	/**
	 * 
	 * @author yangxujia
	 * @date 2015年9月15日下午6:24:40
	 */
	@Query(nativeQuery=true,value="select f.id,f.userId,f.content,f.time,u.headPicture,u.name,u.sex,u.address,u.jinwei from friendCircle f left join user u on u.id=f.userId where geohashCode like ?1 limit 200 ")
	public List<Object[]> findNearbyOrderByDistance(String geohashCode);
	
	
	@Query(nativeQuery=true,value="select id,userId,content,time from friendCircle where userId = ?1 ORDER BY time DESC LIMIT 10; ")
	public List<Object[]> findByUserId(int userId);
}
