package com.naicha.app.dao;

import java.math.BigInteger;

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
}
