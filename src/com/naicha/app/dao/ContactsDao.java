package com.naicha.app.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Contacts;

public interface ContactsDao extends Repository<Contacts, Integer> {

	/**
	 * 保存
	 * @return
	 */
	public Contacts save(Contacts contacts);
	
	@Query(nativeQuery=true,value="select count(1) from contacts "
			+ "where (userAId=?1 and userBId =?2) or(userAId=?2 and userBId=?1) ")
	public BigInteger isExist(int userAId,int userBId);

	/**
	 * 拉黑处理
	 * @author yangxujia
	 * @date 2015年9月26日下午7:02:50
	 */
	@Modifying
	@Query(nativeQuery=true,value="update contacts set isFriend =getIsFriendCode(?1,?2 ) where (userAId=?1 and userBId =?2) or (userAId=?2 and userBId=?1) ")
	public Integer updateIsFriend(int userAId,int userBId);

	/**
	 * 根据用户Id 查找黑名称列表
	 * @author yangxujia
	 * @date 2015年10月9日下午6:20:48
	 */
	@Query(nativeQuery=true,value="")
	public List<Object[]> getBlockedList(Integer userAId);
	
}
