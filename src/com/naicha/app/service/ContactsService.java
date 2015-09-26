package com.naicha.app.service;

import java.math.BigInteger;

import com.naicha.app.mode.Contacts;



public interface ContactsService {

	public Contacts save(Contacts contacts);
	
	public BigInteger isExist(int userAId,int userBId);

	public BigInteger updateIsFriend(Integer userAId, Integer userBId);
}
