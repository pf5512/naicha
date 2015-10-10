package com.naicha.app.service;

import java.math.BigInteger;
import java.util.List;

import com.naicha.app.mode.Contacts;
import com.naicha.web.vo.Blocked;



public interface ContactsService {

	public Contacts save(Contacts contacts);
	
	public BigInteger isExist(int userAId,int userBId);

	public Integer updateIsFriend(Integer userAId, Integer userBId);

	public List<Blocked> getBlockedList(Integer userAId);
}
