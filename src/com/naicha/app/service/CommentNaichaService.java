package com.naicha.app.service;

import java.util.List;

import com.naicha.app.mode.CommentNaicha;
import com.naicha.web.vo.RespComment2Naicha;
import com.naicha.web.vo.RespCommentNaicha;
import com.naicha.web.vo.RespRankCount;



public interface CommentNaichaService {

	public CommentNaicha save(CommentNaicha commentNaicha);

	public List<RespCommentNaicha> findCommentById(Integer userId);
	
	public List<RespRankCount> findRankCount(Integer userId);

	public List<RespComment2Naicha> getCommentNaichaByTimeType(String timeType, String currentPage, String pageSize);

	public List<RespRankCount> findRankCountByTimeType(String timeType);
	
//	public List<CommentNaicha> findByFriendCircleId(List<Integer> condition);
}
