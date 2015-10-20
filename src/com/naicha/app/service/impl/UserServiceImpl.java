package com.naicha.app.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.CommentNaichaDao;
import com.naicha.app.dao.UserDao;
import com.naicha.app.mode.Pictures;
import com.naicha.app.mode.User;
import com.naicha.app.service.PicturesService;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.Geohash;
import com.naicha.web.vo.RespUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CommentNaichaDao	commentNaichaDao;
	@Autowired
	private PicturesService picturesService;

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public Integer updateHeadPicture(String headPicture, Integer userId) {
		return userDao.updateHeadPicture(headPicture,userId);
	}
	
	@Override
	public Integer updateHometown(String hometown, int id) {
		return userDao.updateHometown(hometown, id);
	}

	@Override
	public Integer updateHobby(String hobby, int id) {
		return userDao.updateHobby(hobby, id);
	}

	@Override
	public Integer updateName(String name, Integer userId) {
		return userDao.updateName(name, userId);
	}
	
	@Override
	public Integer updateNaichaNo(String naichaNo, Integer userId) {
		return userDao.updateNaichaNo(naichaNo, userId);
	}

	@Override
	public Integer updatePersonalNote(String personalNote, int id) {
		return userDao.updatePersonalNote(personalNote, id);
	}

	@Override
	public Integer updateSex(Integer sex, Integer userId) {
		return userDao.updateSex(sex, userId);
	}

	@Override
	public Integer updateSid(String sid, int id) {
		return userDao.updateSid(sid, id);
	}

	@Override
	public Integer updateAge(String age, int id) {
		return userDao.updateAge(age, id);
	}
	

	@Override
	public Boolean isExistFindByPhone(String phone) {
		Object[] objects = userDao.findByPhone(phone);
		if(objects==null){
			return false;
		}else{
		return true;
		}
	}
	/**
	 * 通过手机号查找
	 */
	@Override
	public User findByPhone(String phone) {
		Object[] objects = userDao.findByPhone(phone);
		if(objects==null){
			return null;
		}
		User user = new User();
		user.setHeadPicture((String) objects[0]);
		user.setName((String) objects[1]);
		user.setAge((Integer) objects[2]);
		user.setProfession((String) objects[3]);
		user.setAddress((String) objects[4]);
		user.setPhone((String) objects[5]);
		user.setUserType((Integer) objects[6]);
		user.setRegisterTime((Date) objects[7]);
		user.setNaichaNo((String) objects[8]);
		user.setPerSignature((String) objects[9]);
		user.setPassword((String) objects[10]);
		user.setId((Integer)objects[11]);
		user.setSex((Integer) objects[12]);
		user.setBirthday((Date) objects[13]);
		user.setWeiXinNo((String) objects[14]);
		return user;
	}
	/**
	 * 通过手机号查找
	 */
	@Override
	public User findByUserId(Integer userId) {
		Object[] objects = userDao.findByUserId(userId);
		if(objects==null){
			return null;
		}
		User user = new User();
		user.setHeadPicture((String) objects[0]);
		user.setName((String) objects[1]);
		user.setAge((Integer) objects[2]);
		user.setProfession((String) objects[3]);
		user.setAddress((String) objects[4]);
		user.setPhone((String) objects[5]);
		user.setUserType((Integer) objects[6]);
		user.setRegisterTime((Date) objects[7]);
		user.setNaichaNo((String) objects[8]);
		user.setPerSignature((String) objects[9]);
		user.setPassword((String) objects[10]);
		user.setId((Integer)objects[11]);
		user.setSex((Integer) objects[12]);
		user.setBirthday((Date) objects[13]);
		user.setWeiXinNo((String) objects[14]);
		return user;
	}
	
	/**
	 *通过奶茶号查找
	 */
	@Override
	public User findByNaichaNo(String naicha) {
		Object[] objects = userDao.findByNaichaNo(naicha);
		if(objects==null){
			return null;
		}
		User user = new User();
		user.setHeadPicture((String) objects[0]);
		user.setName((String) objects[1]);
		user.setAge((Integer) objects[2]);
		user.setProfession((String) objects[3]);
		user.setAddress((String) objects[4]);
		user.setPhone((String) objects[5]);
		user.setUserType((Integer) objects[6]);
		user.setRegisterTime((Date) objects[7]);
		user.setNaichaNo((String) objects[8]);
		user.setPerSignature((String) objects[9]);
		user.setPassword((String) objects[10]);
		user.setId((Integer)objects[11]);
		user.setSex((Integer) objects[12]);
		user.setBirthday((Date) objects[13]);
		user.setWeiXinNo((String) objects[14]);
		return user;
	}

	@Override
	public Integer updatePassword(Integer userId,String password) {
		Integer retCode = userDao.updatePassword(userId,password);
		return retCode;
	}

	@Override
	public Integer updateBirthday(Date birthday, Integer userId) {
		Integer retCode =userDao.updateBirthday(birthday,userId);
		return retCode;
	}

	@Override
	public Integer updateProfession(String profession, Integer userId) {
		Integer retCode = userDao.updateProfession(userId,profession);
		return retCode;
	}

	@Override
	public Integer updateAddress(Integer userId, String address,String jinwei) {
		String geohashCode = new Geohash().getGeohashCode(jinwei);
		Integer retCode = userDao.updateAddress(userId,address,jinwei,geohashCode);
		//更新到mongodb中
//		String 
		return retCode;
	}

	@Override
	public Integer updatePerSignature(Integer userId, String perSignature) {
		Integer retCode = userDao.updatePerSignature(userId,perSignature);
		return retCode;
	}

	@Override
	public Integer updateWeixinNo(Integer userId, String weixinNo) {
		Integer retCode = userDao.updateWeixinNo(userId,weixinNo);
		return retCode;
	}

	@Override
	public RespUser findTADetail(String userIdStr,String jinwei) {
		RespUser user = new RespUser();
		Integer userId = Integer.parseInt(userIdStr);
		Object [] obj = userDao.findTADetail(userId);
		//id,headPicture,name,sex,birthday,rank,address,profession,serviceType
		// 1    2         3   4      5      6      7      8          9
		user.setId((Integer) obj[0]);
		user.setHeadPicture((String) obj[1]);//头像
		user.setName((String) obj[2]);//名称
		user.setSex((Integer) obj[3]);//性别
		user.setAge(getAge((Date)obj[4]).toString());//年龄
		user.setRank((String) obj[5]);//等级
		user.setAddress((String) obj[6]);//位置
		user.setProfession((String) obj[7]);//职业
		user.setServiceType((Integer) obj[8]);//服务类型
		user.setDistance(getDistance(jinwei,(String) obj[9]));//距离
		user.setIsActive((Integer)obj[10]);//认证标签
		user.setCommentCount(commentNaichaDao.getCommentCountByUserId(Integer.parseInt(userIdStr)).intValue());
		List<Pictures> picList = picturesService.findByUserId(userId);
		user.setPicList(picList);
		return user;
	}
	/**
	 * 雇主详情
	 */
	@Override
	public RespUser findGuzhuDetail(String userIdStr,String jinwei) {
		RespUser user = new RespUser();
		Integer userId = Integer.parseInt(userIdStr);
		Object [] obj = userDao.findTADetail(userId);
		//id,headPicture,name,sex,birthday,rank,address,profession,serviceType
		// 1    2         3   4      5      6      7      8          9
		user.setId((Integer) obj[0]);
		user.setHeadPicture((String) obj[1]);//头像
		user.setName((String) obj[2]);//名称
		user.setSex((Integer) obj[3]);//性别
		user.setAge(getAge((Date)obj[4]).toString());//年龄
		user.setRank((String) obj[5]);//等级
		user.setAddress((String) obj[6]);//位置
		user.setProfession((String) obj[7]);//职业
		user.setServiceType((Integer) obj[8]);//服务类型
		user.setDistance(getDistance(jinwei,(String) obj[9]));//距离
		user.setIsActive((Integer)obj[10]);//认证标签
		List<Pictures> picList = picturesService.findByUserId(userId);
		user.setPicList(picList);
		return user;
	}
	
	private Integer getAge(Date mydate){
		  Date date=new Date();     
		  long day=(date.getTime()-mydate.getTime())/(24*60*60*1000) + 1;
		  Integer year=(int) (day/365);
		return year;
		}
	
	private String getDistance(String jinwei, String friendCircleJinwei) {
		double PI = 3.14159265358979323; // 圆周率
		double R = 6371229; // 地球的半径
		String str[]=jinwei.split(",");
		double longt1= Double.parseDouble(str[0]);
		double lat1 = Double.parseDouble(str[1]);
		String str2[] = friendCircleJinwei.split(",");
		double longt2=Double.parseDouble(str2[0]);
		double lat2=Double.parseDouble(str2[1]);
		double x, y, distance;
		x = (longt2 - longt1) * PI * R
				* Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y)/1000;
		BigDecimal bg = new BigDecimal(distance);
		String j = bg.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		return j+"km";
	}

	@Override
	public Integer oralIdentify(String profession, String rank, Integer userId,
			String pics,String weixinNo,String phone) {
		return userDao.udateOralIdentify(profession,Integer.parseInt(rank),userId,pics,weixinNo,phone);
	}

	@Override
	public Integer updatePhone(Integer userId, String phone) {
		return userDao.updatePhone(userId,phone);
	}

	@Override
	public User findById(int userId) {
		Object[] objects = userDao.findById(userId);
		if(objects==null){
			return null;
		}
		User user = new User();
		user.setHeadPicture((String) objects[0]);
		user.setName((String) objects[1]);
		user.setAge((Integer) objects[2]);
		user.setProfession((String) objects[3]);
		user.setAddress((String) objects[4]);
		user.setPhone((String) objects[5]);
		user.setUserType((Integer) objects[6]);
		user.setRegisterTime((Date) objects[7]);
		user.setNaichaNo((String) objects[8]);
		user.setPerSignature((String) objects[9]);
		user.setPassword((String) objects[10]);
		return user;
	}

	@Override
	public Integer updatePasswordByPhone(String phone, String password) {
		Integer retCode = userDao.updatePasswordByPhone(phone,password);
		return retCode;
	}

	@Override
	public Integer updateMyServiceTime(Integer userId, String myServiceTime) {
		Integer retCode = userDao.updatePasswordByPhone(userId,myServiceTime);
		return retCode;
	}

	@Override
	public String getMyServiceTime(Integer userId) {
		String myServiceTimeString = userDao.getMyServiceTime(userId);
		return myServiceTimeString;
	}

	@Override
	public Integer updateServiceType(Integer userId, String serviceType) {
		Integer retCode = userDao.updateServiceType(userId,serviceType);
		return retCode;
	}
	
	@Override
	public String getServiceType(Integer userId) {
		String serviceType = userDao.getServiceType(userId);
		return serviceType;
	}
}
