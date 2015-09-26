package com.naicha.app.service;

import java.math.BigInteger;
import java.util.List;

import com.naicha.app.mode.CommentNaicha;
import com.naicha.web.vo.RespCommentNaicha;



public interface CommentNaichaService {

	public CommentNaicha save(CommentNaicha commentNaicha);

	public List<RespCommentNaicha> findCommentById(Integer userId);
	
	public List<BigInteger> findRankCount(Integer userId);
	
//	public List<CommentNaicha> findByFriendCircleId(List<Integer> condition);
}
