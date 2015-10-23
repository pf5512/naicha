package com.naicha.app.dao;

import org.springframework.data.repository.Repository;

import com.naicha.app.mode.AlipayResult;

public interface AlipayResultDao extends Repository<AlipayResult, Integer>{
	public AlipayResult save(AlipayResult alipayResult);
}
