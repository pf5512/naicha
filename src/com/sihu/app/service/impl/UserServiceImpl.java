package com.sihu.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sihu.app.dao.UserDao;
import com.sihu.app.mode.User;
import com.sihu.app.service.UserService;

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
	public User findByPhone(String phone) {
		Object[] objects = userDao.findByPhone(phone);
		if(objects==null){
			return null;
		}
		User user = new User();
		user.setId((Integer) objects[0]);
		user.setUsername((String) objects[1]);
		user.setPassword((String) objects[2]);
		user.setSid((String) objects[3]);
		user.setAge((Integer) objects[4]);
		user.setPhone((String) objects[5]);
		user.setXx((Double) objects[6]);
		user.setYy((Double) objects[7]);
		user.setValidateState((Integer) objects[8]);
		user.setHeadPicture((String) objects[9]);
		user.setRegistrantionTime((Date) objects[10]);
		user.setEmail((String) objects[11]);
		user.setHometown((String) objects[12]);
		user.setSex((Integer) objects[13]);
		user.setHobby((String) objects[14]);
		user.setPersonalNote((String) objects[15]);
		return user;
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
	
}
