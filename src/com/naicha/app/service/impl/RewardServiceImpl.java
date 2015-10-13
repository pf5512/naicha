package com.naicha.app.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.RewardDao;
import com.naicha.app.mode.Reward;
import com.naicha.app.service.RewardService;

@Service
@Transactional
public class RewardServiceImpl implements RewardService {

	@Autowired
	private RewardDao rewardDao;

	@Override
	public Reward save(Reward reward) {
		return rewardDao.save(reward);
	}

	@Override
	public List<Reward> getRewardList() {
		List<Reward> rewardList =  new ArrayList<Reward>();
		List<Object[]> objList =  rewardDao.getRewardList();
		for (Object[] objects : objList) {
			Reward  reward = new Reward();
			reward.setId((Integer) objects[0]);
			reward.setReward((Integer) objects[1]);
			rewardList.add(reward);
		}
		return rewardList;
	}

	@Override
	public Integer deleteById(String id) {
		return rewardDao.deleteById(Integer.parseInt(id));
	}

	@Override
	public Integer deleteByReward(String rewardStr) {
		return rewardDao.deleteByReward(Integer.parseInt(rewardStr));
	}

	@Override
	public BigInteger isExist(String rewardString) {
		return rewardDao.findByReward(rewardString);
	}
}
