package com.naicha.app.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Reward;

public interface RewardDao extends Repository<Reward, Integer> {
	
	/**
	 * 增加一条数据
	 * @author yangxujia
	 * @date 2015年10月12日下午2:23:02
	 */
	public Reward save(Reward reward);
	
	/**
	 * 获取奖金列表信息
	 * @author yangxujia
	 * @date 2015年10月12日下午2:23:19
	 */
	@Query(nativeQuery=true,value="select id,reward from reward order by reward asc ")
	public List<Object[]> getRewardList();
	
	/**
	 * 删除一条记录
	 * @author yangxujia
	 * @date 2015年10月12日下午2:24:34
	 */
	@Modifying
	@Query(nativeQuery=true,value="delete from reward where id = ?1")
	public Integer deleteById(int id);

	@Modifying
	@Query(nativeQuery=true,value="delete from reward where reward = ?1")
	public Integer deleteByReward(int reward);
	
	//根据奖金来搜索
	@Query(nativeQuery=true,value="select count(1) from reward where reward = ?1")
	public BigInteger findByReward(String rewardString);
	
}
