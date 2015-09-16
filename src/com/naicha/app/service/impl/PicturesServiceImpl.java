package com.naicha.app.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

	@Override
	public List<Pictures> findByFriendCircleId(List<Integer> condition) {
		List<Object[]>objList = picturesDao.findByFriendCircleId(condition);
		// id,path, friendCircleId,hight,width,time
		// 0   1      2             3      4    5
		List<Pictures> picList = new ArrayList<Pictures>();
		for (Object[] obj : objList) {
			Pictures picture = new Pictures();
			picture.setId((Integer)obj[0]);
			picture.setPath((String) obj[1]);
			picture.setFriendCircleId((Integer) obj[2]);
			picture.setHight((Integer) obj[3]);
			picture.setWidth((Integer) obj[4]);
			picture.setTime((Date) obj[5]);
			picList.add(picture);
		}
		return picList;
	}
	
}
