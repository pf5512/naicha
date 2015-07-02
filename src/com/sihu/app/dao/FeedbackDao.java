package com.sihu.app.dao;

import org.springframework.data.repository.Repository;

import com.sihu.app.mode.Feedback;




public interface FeedbackDao extends Repository<Feedback, Integer> {

	/**
	 * 保存
	 * @return
	 */
	public Feedback save(Feedback feedback);
}
