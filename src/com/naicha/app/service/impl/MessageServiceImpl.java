package com.naicha.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.ApplyDao;
import com.naicha.app.dao.MessageDao;
import com.naicha.app.mode.Apply;
import com.naicha.app.mode.Message;
import com.naicha.app.service.ApplyService;
import com.naicha.app.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public Message save(Message message) {
		return messageDao.save(message);
	}

	
}
