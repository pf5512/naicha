package com.naicha.app.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naicha.app.dao.CommentNaichaDao;
import com.naicha.app.mode.CommentNaicha;
import com.naicha.app.service.CommentNaichaService;
import com.naicha.web.vo.RespCommentNaicha;

@Service
@Transactional
public class CommentNaichaServiceImpl implements CommentNaichaService {

	@Autowired
	private CommentNaichaDao commentNaichaDao;

	@Override
	public CommentNaicha save(CommentNaicha CommentNaicha) {
		return commentNaichaDao.save(CommentNaicha);
	}

	@Override
	public List<RespCommentNaicha> findCommentById(Integer userId) {
		List<Object[]> objList = commentNaichaDao.findCommentById(userId);
		List<RespCommentNaicha> respCommentNaichaList = new ArrayList<RespCommentNaicha>();
		for (Object[] obj : objList) {
			RespCommentNaicha respCommentNaicha =  new RespCommentNaicha();
			//c.rank,c.content,c.beCommentId,c.toCommentId,c.time,u.name
			// 0        1         2              3            4     5
			respCommentNaicha.setRank((Integer) obj[0]);
			respCommentNaicha.setContent((String) obj[1]);
			respCommentNaicha.setBeCommentId((Integer) obj[2]);
			respCommentNaicha.setToCommentId((Integer) obj[3]);
			respCommentNaicha.setTime((Date) obj[4]);
			respCommentNaicha.setName((String) obj[5]);
			respCommentNaichaList.add(respCommentNaicha);
		}
		return respCommentNaichaList;
	}
	
	public List<BigInteger> findRankCount(Integer userId){
		return  commentNaichaDao.findRankCount(userId);
	}
}
