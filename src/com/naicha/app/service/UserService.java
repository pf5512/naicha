package com.naicha.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.naicha.app.mode.Task;
import com.naicha.app.mode.User;
import com.naicha.web.vo.RespUser;


public interface UserService {

	/**
	 * 添加到用户表中
	 * @author yangxujia
	 */
	public User save(User user);
	
	/**
	 * 通过手机号码查询User对象
	 * @author yangxujia
	 * @date 2015-1-4下午10:45:16
	 */
	public User findByPhone(String phone);
	
	/**
	 * 更新头像
	 * @author yangxujia
	 * @date 2015年9月11日上午11:55:25
	 */
	public Integer updateHeadPicture(String headPicture, Integer userId);
	
	/**
	 * 更新姓名
	 * @author yangxujia
	 * @date 2015年9月11日下午4:11:20
	 */
	public Integer updateName(String name, Integer userId);

	/**
	 * 更新奶茶号
	 * @author yangxujia
	 * @date 2015年9月11日下午4:52:59
	 */
	public Integer updateNaichaNo(String naichaNo, Integer userId);
	
	/**
	 * 更改性别
	 * @author yangxujia
	 * @date 2015年9月11日下午5:02:45
	 */
	public Integer updateSex(Integer sex, Integer userId);
	
	/**
	 * 更新出生年月
	 * @author yangxujia
	 * @date 2015年9月11日下午5:11:48
	 */
	public Integer updateBirthday(Date birthday,Integer userId);
	
	public Integer updateHometown(String hometown, int id);

	public Integer updateHobby(String hobby, int id);

	public Integer updatePersonalNote(String personalNote, int id);

	public Integer updateSid(String sid, int id);
	
	public Integer updateAge(String age, int id);

	public Boolean isExistFindByPhone(String phone);

	public User findByNaichaNo(String phoneOrNaicha);

	public Integer updatePassword(Integer userId,String password);

	public Integer updateProfession(String profession, Integer userId);

	public Integer updateAddress(Integer userId, String address, String jinwei);
	
	public Integer updatePerSignature(Integer userId, String perSignature);
	
	public Integer updateWeixinNo(Integer userId, String weixinNo);

	public RespUser findTADetail(String userIdStr , String jinwei);

	public Integer oralIdentify(String profession, String rank, Integer userId,	String pics, String weixinNo, String phone);

	public RespUser findGuzhuDetail(String userIdStr, String jinwei);

	public Integer updatePhone(Integer userId, String phone);

	public User findById(int userId);

	public Integer updatePasswordByPhone(String phone, String password);

	public Integer updateMyServiceTime(Integer userId, String myServiceTime);

	public String getMyServiceTime(Integer userId);

	public Integer updateServiceType(Integer userId, String serviceType);

	public String getServiceType(Integer userId);

	public User findByUserId(Integer userId);
}
