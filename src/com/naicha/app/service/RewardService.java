package com.naicha.app.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;

import com.naicha.app.mode.Reward;



public interface RewardService {

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
	public List<Reward> getRewardList();
	
	/**
	 * 根据Id
	 * 删除一条记录
	 * @author yangxujia
	 * @date 2015年10月12日下午2:24:34
	 */
	public Integer deleteById(String id);

	
	/**
	 * 根据奖金档位
	 * 删除一条记录
	 * @author yangxujia
	 * @date 2015年10月12日下午2:24:34
	 */
	public Integer deleteByReward(String rewardStr);

	public BigInteger isExist(String rewardString);
}
