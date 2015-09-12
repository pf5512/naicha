package com.naicha.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.FriendCircleDao;
import com.naicha.app.mode.FriendCircle;
import com.naicha.app.service.FriendCircleService;

@Service
@Transactional
public class FriendCircleServiceImpl implements FriendCircleService {

	@Autowired
	private FriendCircleDao friendCircleDao;
	
	@Override
	public FriendCircle save(FriendCircle friendCircle) {
		return friendCircleDao.save(friendCircle);
	}
}
