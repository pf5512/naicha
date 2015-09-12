package com.naicha.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.UserDao;
import com.naicha.app.mode.User;
import com.naicha.app.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public Integer updateHeadPicture(String headPicture, String phone) {
		return userDao.updateHeadPicture(headPicture,phone);
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
	public Integer updateName(String name, String phone) {
		return userDao.updateName(name, phone);
	}
	
	@Override
	public Integer updateNaichaNo(String naichaNo, String phone) {
		return userDao.updateNaichaNo(naichaNo, phone);
	}

	@Override
	public Integer updatePersonalNote(String personalNote, int id) {
		return userDao.updatePersonalNote(personalNote, id);
	}

	@Override
	public Integer updateSex(Integer sex, String phone) {
		return userDao.updateSex(sex, phone);
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
		return user;
	}

	@Override
	public Integer updatePassword(String phone,String password) {
		Integer retCode = userDao.updatePassword(phone,password);
		return retCode;
	}

	@Override
	public Integer updateBirthday(Date birthday, String phone) {
		Integer retCode =userDao.updateBirthday(birthday,phone);
		return retCode;
	}

	@Override
	public Integer updateProfession(String profession, String phone) {
		Integer retCode = userDao.updateProfession(phone,profession);
		return retCode;
	}

	@Override
	public Integer updateAddress(String phone, String address) {
		Integer retCode = userDao.updateAddress(phone,address);
		return retCode;
	}

	@Override
	public Integer updatePerSignature(String phone, String perSignature) {
		Integer retCode = userDao.updatePerSignature(phone,perSignature);
		return retCode;
	}

	@Override
	public Integer updateWeixinNo(String phone, String weixinNo) {
		Integer retCode = userDao.updateWeixinNo(phone,weixinNo);
		return retCode;
	}
}
