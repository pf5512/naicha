package com.naicha.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.PicturesDao;
import com.naicha.app.mode.FriendCircle;
import com.naicha.app.mode.Pictures;
import com.naicha.app.service.PicturesService;

@Service
@Transactional
public class PicturesServiceImpl implements PicturesService {

	@Autowired
	private PicturesDao picturesDao;

	@Override
	public Pictures save(Pictures pictures) {
		return picturesDao.save(pictures);
	}
	
}
