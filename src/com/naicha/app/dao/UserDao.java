package com.naicha.app.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.User;




public interface UserDao extends Repository<User, Integer> {

	/**
	 * 保存
	 * @return
	 */
	public User save(User user);

	/**
	 * 通过手机号码查找用户
	 * @author yangxujia
	 * @date 2015年9月9日上午9:55:02
	 */
	@Query(nativeQuery=true,value="SELECT picturePath, name,age,profession,address,phone,userType,regitsterTime,naichaNo,perSignature,password FROM user where phone=?1 limit 1")
	public Object[] findByPhone(String phone);

	/**
	 * 通过奶茶号查找用户
	 * @author yangxujia
	 * @date 2015年9月9日上午9:55:02
	 */
	@Query(nativeQuery=true,value="SELECT picturePath, name,age,profession,address,phone,userType,regitsterTime,naichaNo,perSignature,password FROM user where naichaNo=?1 limit 1")
	public Object[] findByNaichaNo(String naicha);
	/**
	 * 根据id更改头像
	 * @author yangxujia
	 * @date 2015-1-26下午11:23:30
	 */
	@Modifying
	@Query(nativeQuery=true,value="update user set headPicture=?1 where id=?2")
	public Integer updateHeadPicture(String headPicture, Integer id);

	/**
	 * 更改性别
	 */
	@Modifying
	@Query(nativeQuery=true,value="update user set sex=?1 where id=?2")
	public Integer updateSex(String sex, Integer id);

	@Modifying
	@Query(nativeQuery=true,value="update user set hometown=?1 where id=?2")
	public Integer updateHometown(String hometown, int id);

	@Modifying
	@Query(nativeQuery=true,value="update user set hobby=?1 where id=?2")
	public Integer updateHobby(String hobby, int id);

	@Modifying
	@Query(nativeQuery=true,value="update user set username=?1 where id=?2")
	public Integer updateUsername(String username, int id);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set sid=?1 where id=?2")
	public Integer updateSid(String sid, int id);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set age=?1 where id=?2")
	public Integer updateAge(String age, int id);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set personalNote=?1 where id=?2")
	public Integer updatePersonalNote(String personalNote, int id);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set password=?1 where id=?2")
	public Integer resetPassword(String password, int id);


}
