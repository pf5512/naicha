package com.naicha.app.dao;

import org.springframework.data.repository.Repository;

import com.naicha.app.mode.JournalAccount;

public interface JournalAccountDao extends Repository<JournalAccount, Integer>{
	
	public JournalAccount save(JournalAccount journalAccount);
	
	
}
