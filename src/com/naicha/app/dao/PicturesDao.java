package com.naicha.app.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Pictures;

public interface PicturesDao extends Repository<Pictures, Integer> {

	/**
	 * 保存图片
	 * @author yangxujia
	 * @date 2015年9月14日上午9:26:25
	 */
	public Pictures save(Pictures pictures);
	
	/**
	 * 根据朋友圈id列表查找所有的图片
	 * @author yangxujia
	 * @date 2015年9月16日下午3:36:39
	 */
	@Query(nativeQuery=true,value="select id,path, friendCircleId,hight,width,time from pictures where friendCircleId in (?1) ")
	public List<Object[]> findByFriendCircleId(Collection<Integer> condition);
}
