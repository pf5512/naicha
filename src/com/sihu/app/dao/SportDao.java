package com.sihu.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.sihu.app.mode.Sport;

public interface SportDao extends Repository<Sport, Integer> {

	/**
	 * 保存活动
	 * 
	 * @author yangxujia
	 * @date 2015-1-21下午10:25:57
	 * @param sport
	 */
	public Sport save(Sport sport);

	/**
	 * 根据查询前20条，并且按距离进行排序
	 * 
	 * @author yangxujia
	 * @date 2015-1-21下午10:25:47
	 * @param x
	 * @param y
	 */
	@Query(nativeQuery = true, value = "select s.*, 6378137*2*asin(Sqrt(power(sin((?2-s.xx)*pi()/360),2)  + Cos(?2*pi()/180)*Cos(s.xx*pi()/180)*power(sin((?1-s.yy)*pi()/360),2)))"
			+ " as distance,u.headPicture  from sport s left JOIN user u  on s.userId=u.id  order by distance ASC limit 20")
	public List<Object[]> findAll(Double x, Double y);

	/**
	 * 根据运动类型进行排序
	 * 
	 * @author yangxujia
	 * @date 2015-1-21下午10:30:03
	 * @param x
	 * @param y
	 * @param sports
	 */
	@Query(nativeQuery = true, value = "select s.*, 6378137*2*asin(Sqrt(power(sin((?2-xx)*pi()/360),2)  + Cos(?2*pi()/180)*Cos(xx*pi()/180)*power(sin((?1-yy)*pi()/360),2)))"
			+ " as distance,u.headPicture  from sport s left JOIN user u  on s.userId=u.id  where s.sports=?3 order by distance ASC limit 20")
	public List<Object[]> findBySportType(Double x, Double y, String sports);

	@Query(nativeQuery = true, value = "select s.*, 6378137*2*asin(Sqrt(power(sin((?2-xx)*pi()/360),2)  + Cos(?2*pi()/180)*Cos(xx*pi()/180)*power(sin((?1-yy)*pi()/360),2)))"
			+ " as distance,u.headPicture  from sport s left JOIN user u  on s.userId=u.id  where s.startTime>=?3 order by distance ASC limit 20")
	public List<Object[]> findByDateTime(Double x, Double y, String time);

	public Sport findOne(Integer i);
}
