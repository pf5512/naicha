package com.naicha.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.UserDao;
import com.naicha.app.mode.User;
import com.naicha.app.service.UserService;
import com.naicha.web.vo.RespUser;

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
	public Integer updateHeadPicture(String headPicture, Integer id) {
		return userDao.updateHeadPicture(headPicture,id);
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
	public Integer updateUsername(String username, int id) {
		return userDao.updateUsername(username, id);
	}

	@Override
	public Integer updatePersonalNote(String personalNote, int id) {
		return userDao.updatePersonalNote(personalNote, id);
	}

	@Override
	public Integer updateSex(String sex, Integer id) {
		return userDao.updateSex(sex, id);
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
	public Integer resetPassword(String password, int id) {
		return userDao.resetPassword(password, id);
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
		user.setPicturePath((String) objects[0]);
		user.setName((String) objects[1]);
		user.setAge((Integer) objects[2]);
		user.setProfession((String) objects[3]);
		user.setAddress((String) objects[4]);
		user.setPhone((String) objects[5]);
		user.setUserType((String) objects[6]);
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
		user.setPicturePath((String) objects[0]);
		user.setName((String) objects[1]);
		user.setAge((Integer) objects[2]);
		user.setProfession((String) objects[3]);
		user.setAddress((String) objects[4]);
		user.setPhone((String) objects[5]);
		user.setUserType((String) objects[6]);
		user.setRegisterTime((Date) objects[7]);
		user.setNaichaNo((String) objects[8]);
		user.setPerSignature((String) objects[9]);
		user.setPassword((String) objects[10]);
		return user;
	}
}
