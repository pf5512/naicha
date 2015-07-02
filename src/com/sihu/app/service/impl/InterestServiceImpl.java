package com.sihu.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihu.app.dao.InterestDao;
import com.sihu.app.mode.Interest;
import com.sihu.app.service.InterestService;

@Service
@Transactional
public class InterestServiceImpl implements InterestService {

	@Autowired
	private InterestDao interestDao;

	/**
	 * 保存趣闻
	 */
	@Override
	public Interest save(Interest interest) {
		return interestDao.save(interest);
	}

	@Override
	public List<Interest> findAll(String location) {
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		List<Interest> listInterest = new ArrayList<Interest>();
		List<Object[]> listObject = interestDao.findAll(x,y);
		listInterest = this.convert(listObject);
		return listInterest;
	}

	private List<Interest> convert(List<Object[]> listObject) {
		List<Interest> listInterest = new ArrayList<Interest>();
				
		for (Object[] objects : listObject) {
			Interest interest = new Interest();
			interest.setId((Integer) objects[0]);
			interest.setUserId((Integer) objects[1]);
			interest.setContent((String) objects[2]);
			interest.setTime((Date) objects[3]);
			interest.setXx((Double) objects[4]);
			interest.setYy((Double) objects[5]);
			interest.setClickCount((Integer) objects[6]);
			interest.setDistance((Double) objects[7]);
			interest.setHeadPicture((String) objects[8]);
			listInterest.add(interest);
		}
		return listInterest;
	}

	@Override
	public Interest findById(String location, String id) {
		return interestDao.findOne(Integer.parseInt(id));
	}

	
}
 