package com.naicha.app.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
	@Query(nativeQuery=true,value="SELECT headPicture, name,age,profession,address,phone,userType,regitsterTime,naichaNo,perSignature,password,id,sex,birthday,weixinNo FROM user where phone=?1 limit 1")
	public Object[] findByPhone(String phone);

	/**
	 * 通过奶茶号查找用户
	 * @author yangxujias
	 * @date 2015年9月9日上午9:55:02
	 */
	@Query(nativeQuery=true,value="SELECT headPicture, name,age,profession,address,phone,userType,regitsterTime,naichaNo,perSignature,password,id,sex,birthday,weixinNo FROM user where naichaNo=?1 limit 1")
	public Object[] findByNaichaNo(String naicha);
	
	/**
	 * 更新名称
	 * @author yangxujia
	 * @date 2015年9月11日下午4:19:34
	 */
	@Modifying
	@Query(nativeQuery=true,value="update user set name=?1 where id=?2")
	public Integer updateName(String name, int userId);
	
	/**
	 * 根据phone更改头像
	 * @author yangxujia
	 * @date 2015年9月11日下午4:55:24
	 */
	@Modifying
	@Query(nativeQuery=true,value="update user set headPicture=?1 where id=?2")
	public Integer updateHeadPicture(String headPicture, int userId);

	/**
	 * 更新奶茶号
	 * @author yangxujia
	 * @date 2015年9月11日下午4:55:24
	 */
	@Modifying
	@Query(nativeQuery=true,value="update user set naichaNo=?1 where id=?2")
	public Integer updateNaichaNo(String naichaNo, int userId);
	
	/**
	 * 更改性别
	 * @author yangxujia
	 * @date 2015年9月11日下午4:58:21
	 */
	@Modifying
	@Query(nativeQuery=true,value="update user set sex=?1 where id=?2")
	public Integer updateSex(Integer sex, int userId);

	@Modifying
	@Query(nativeQuery=true,value="update user set hometown=?1 where id=?2")
	public Integer updateHometown(String hometown, int id);

	@Modifying
	@Query(nativeQuery=true,value="update user set hobby=?1 where id=?2")
	public Integer updateHobby(String hobby, int id);
	
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
	@Query(nativeQuery=true,value="update user set password=?2 where id=?1")
	public Integer updatePassword(int userId,String password);

	@Modifying
	@Query(nativeQuery=true,value="update user set birthday=?1 where id=?2")
	public Integer updateBirthday(Date birthday, int userId);

	@Modifying
	@Query(nativeQuery=true,value="update user set profession=?2 where id=?1")
	public Integer updateProfession(int userId, String profession);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set address=?2,jinwei=?3, geohashCode=?4 where id=?1")
	public Integer updateAddress(int userId, String address, String jinwei,String geohashCode);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set perSignature=?2 where id=?1")
	public Integer updatePerSignature(int userId, String perSignature);
	
	@Modifying
	@Query(nativeQuery=true,value="update user set weixinNo=?2 where id=?1")
	public Integer updateWeixinNo(int userId, String weixinNo);
	
	@Query(nativeQuery=true,value="select id,headPicture,name,sex,birthday,rank,address,profession,serviceType from user where id IN(?1) and isActivate=1 ")
	public List<Object[]> findTA(Collection<Integer> condition);
	
	@Query(nativeQuery=true,value="select id,headPicture,name,sex,birthday,rank,address,profession,serviceType from user where userType=0 and isActivate=1 ORDER BY rank DESC limit ?1 , ?2 ")
	public List<Object[]> findTAByRank(int a,int b);
	
	@Query(nativeQuery=true,value="select id,headPicture,name,sex,birthday,rank,address,profession,serviceType,jinwei,isActivate from user where id = ?1 ")
	public Object[] findTADetail(int a);

	@Modifying
	@Query(nativeQuery=true,value="update user set profession=?1,rank=?2,certificatePicture=?4,weixinNo=?5,phoneForCertificate=?6 where id=?3")
	public Integer udateOralIdentify(String profession, int rank,Integer userId, String pics,String weixinNo,String phoneForCertificate);

	@Modifying
	@Query(nativeQuery=true,value="update user set phone =?2 where id =?1")
	public Integer updatePhone(Integer userId, String phone);

	@Query(nativeQuery=true,value="SELECT headPicture, name,age,profession,address,phone,userType,regitsterTime,naichaNo,perSignature,password FROM user where id=?1 limit 1")
	public Object[] findById(int userId);

	@Modifying
	@Query(nativeQuery=true,value="update user set password=?2 where phone=?1")
	public Integer updatePasswordByPhone(String phone, String password);

	//更新个人可服务时间段
	@Modifying
	@Query(nativeQuery=true,value="update user set myServiceTime=?2 where id=?1")
	public Integer updatePasswordByPhone(Integer userId, String myServiceTime);

	//获取个人服务时间
	@Query(nativeQuery=true,value="select myServiceTime from user where id=?1")
	public String getMyServiceTime(Integer userId);

	//更新我的技能
	@Modifying
	@Query(nativeQuery=true,value="update user set serviceType=?2 where id=?1")
	public Integer updateServiceType(Integer userId, String serviceType);
	
	//获取我的技能
	@Query(nativeQuery=true,value="select serviceType from user where id=?1")
	public String getServiceType(Integer userId);
	
	/**
	 * 根据id查找用户信息
	 */
	@Query(nativeQuery=true,value="SELECT headPicture, name,age,profession,address,phone,userType,regitsterTime,naichaNo,perSignature,password,id,sex,birthday,weixinNo FROM user where id=?1 limit 1")
	public Object[] findByUserId(int userId);

}
