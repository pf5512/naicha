package com.naicha.app.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.ContactsDao;
import com.naicha.app.mode.Contacts;
import com.naicha.app.service.ContactsService;
import com.naicha.web.vo.Blocked;

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
	public Integer updateIsFriend(Integer userAId, Integer userBId) {
		return contactsDao.updateIsFriend(userAId, userBId);
	}

	@Override
	public List<Blocked> getBlockedList(Integer userAId) {
		List<Object[]> objList =  contactsDao.getBlockedList(userAId);
		return null;
	}
}
