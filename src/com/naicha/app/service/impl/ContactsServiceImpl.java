package com.naicha.app.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.ContactsDao;
import com.naicha.app.mode.Contacts;
import com.naicha.app.service.ContactsService;

@Service
@Transactional
public class ContactsServiceImpl implements ContactsService {

	@Autowired
	private ContactsDao contactsDao;

	@Override
	public Contacts save(Contacts contacts) {
		return contactsDao.save(contacts);
	}
	
	@Override
	public BigInteger isExist(int userAId,int userBId){
		return contactsDao.isExist(userAId, userBId);
	}

	@Override
	public BigInteger updateIsFriend(Integer userAId, Integer userBId) {
		return contactsDao.updateIsFriend(userAId, userBId);
	}
}
