package com.naicha.app.dao;

import org.springframework.data.repository.Repository;

import com.naicha.app.mode.Account;

public interface AccountDao extends Repository<Account, Integer>{
	public Account save(Account account);

	
	
}
