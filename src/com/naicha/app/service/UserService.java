package com.naicha.app.service;

import com.naicha.app.mode.User;
import com.naicha.web.vo.RespUser;


public interface UserService {

	/**
	 * 添加到用户表中
	 * @param appNews
	 * @return
	 */
	public User save(User user);
	
	/**
	 * 通过手机号码查询User对象
	 * @author yangxujia
	 * @date 2015-1-4下午10:45:16
	 * @param phone
	 * @return
	 */
	public User findByPhone(String phone);
	
	public Integer updateHeadPicture(String headPicture, Integer id);

	public Integer updateHometown(String hometown, int id);

	public Integer updateHobby(String hobby, int id);

	public Integer updateUsername(String username, int id);

	public Integer updatePersonalNote(String personalNote, int id);
	
	public Integer updateSex(String sex, Integer id);

	public Integer updateSid(String sid, int id);
	
	public Integer updateAge(String age, int id);

	public Integer resetPassword(String password, int id);

	public Boolean isExistFindByPhone(String phone);

	public User findByNaichaNo(String phoneOrNaicha);

}
