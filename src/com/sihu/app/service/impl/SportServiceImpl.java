package com.sihu.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihu.app.dao.SportDao;
import com.sihu.app.mode.Sport;
import com.sihu.app.service.SportService;

@Service
@Transactional
public class SportServiceImpl implements SportService {

	@Autowired
	private SportDao sportDao;

	/**
	 * 保存活动（运动）内容
	 */
	@Override
	public Sport save(Sport sport) {
		return sportDao.save(sport);
	}

	/**
	 * 根据地理坐标查找出附件的运动，按距离排序
	 */
	@Override
	public List<Sport> findAll(String location) {
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		List<Sport> listSport = new ArrayList<Sport>();
		List<Object[]> listObject = sportDao.findAll(x,y);
		listSport = this.convert(listObject);
		return listSport;
	}

	private List<Sport> convert(List<Object[]> listObject){
		List<Sport> listSport = new ArrayList<Sport>();

		for (Object[] objects : listObject) {
			Sport sport = new Sport();
			sport.setId((Integer) objects[0]);
			sport.setTitle((String) objects[1]);
			sport.setSports((String) objects[2]);
			sport.setStartTime((Date) objects[3]);
			sport.setEndTime((Date) objects[4]);
			sport.setAddress((String) objects[5]);
			sport.setDescription((String) objects[6]);
			sport.setUserId((Integer) objects[7]);
			sport.setXx((Double) objects[8]);
			sport.setYy((Double) objects[9]);
			sport.setPublicTime((Date) objects[10]);
			sport.setJoinNum((Integer) objects[11]);
			sport.setClickCount((Integer) objects[12]);
			sport.setDistance((Double) objects[13]);
			sport.setHeadPicture((String) objects[14]);
			listSport.add(sport);
		}
		return listSport;
	}
	@Override
	public List<Sport> findBySportType(String location, String sportType) {
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		List<Sport> listSport = new ArrayList<Sport>();
		List<Object[]> listObject = sportDao.findBySportType(x,y,sportType);
		listSport = this.convert(listObject);
		return listSport;
	}

	@Override
	public List<Sport> findByDateTime(String location, String time) {
		String[] xy=location.split(",");
		Double x = new Double(xy[0]); 
		Double y = new Double(xy[1]);
		List<Sport> listSport = new ArrayList<Sport>();
		List<Object[]> listObject = sportDao.findByDateTime(x,y,time);
		listSport = this.convert(listObject);
		return listSport;
	}

	@Override
	public Sport findById(String location, String id) {
		return sportDao.findOne(Integer.parseInt(id));
	} 
}
 