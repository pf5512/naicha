package com.sihu.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihu.app.dao.FeedbackDao;
import com.sihu.app.mode.Feedback;
import com.sihu.app.service.FeedbackService;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public Feedback save(Feedback feedback) {
		return feedbackDao.save(feedback);
	}

}
 