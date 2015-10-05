package com.naicha.app.dao;

import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Message;

public interface MessageDao extends Repository<Message, Integer>{
	
	public Message save(Message message);
	
	
}
