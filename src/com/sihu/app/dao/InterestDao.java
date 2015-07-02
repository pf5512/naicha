package com.sihu.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.sihu.app.mode.Interest;

public interface InterestDao extends Repository<Interest, Integer> {

	/**
	 * 保存趣闻
	 * @author yangxujia
	 * @date 2015-1-21下午10:25:57
	 * @param interest
	 */
	public Interest save(Interest interest);

	@Query(nativeQuery=true,value="select s.*, 6378137*2*asin(Sqrt(power(sin((?2-s.xx)*pi()/360),2)  + Cos(?2*pi()/180)*Cos(s.xx*pi()/180)*power(sin((?1-s.yy)*pi()/360),2)))"
			+ " as distance,u.headPicture  from interest s left JOIN user u  on s.user_id=u.id  order by distance ASC limit 20")
	public List<Object[]> findAll(Double x, Double y);

	public Interest findOne(Integer parseInt);

}
