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
import com.naicha.web.vo.RespComment2Naicha;
import com.naicha.web.vo.RespCommentNaicha;
import com.naicha.web.vo.RespRankCount;

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
	
	public List<RespRankCount> findRankCount(Integer userId){
		List<Object[]> objList =  commentNaichaDao.findRankCount(userId);
		List<RespRankCount>  respRankCountList =  new ArrayList<RespRankCount>();
		for (Object[] objects : objList) {
			RespRankCount respRankCount = new RespRankCount();
			respRankCount.setRank((Integer) objects[0]);
			respRankCount.setCount((BigInteger) objects[1]);
			respRankCountList.add(respRankCount);
		}
		return respRankCountList;
	}
	
	public List<RespRankCount> findRankCountByTimeType(String  timeType){
		List<Object[]> objList = new ArrayList<Object[]>();
		List<RespRankCount>  respRankCountList =  new ArrayList<RespRankCount>();
		if ("1".contains(timeType)) {
			objList =   commentNaichaDao.findRankCountToday();
		}
		if ("2".contains(timeType)) {
			objList =   commentNaichaDao.findRankCountThisWeek();
		}
		if ("3".contains(timeType)) {
			objList =   commentNaichaDao.findRankCountThisMonth();
		}
		for (Object[] objects : objList) {
			RespRankCount respRankCount = new RespRankCount();
			respRankCount.setRank((Integer) objects[0]);
			respRankCount.setCount((BigInteger) objects[1]);
			respRankCountList.add(respRankCount);
		}
		return respRankCountList;
	}

	@Override
	public List<RespComment2Naicha> getCommentNaichaByTimeType(String timeType, String currentPage, String pageSize) {
		List<Object[]> objList =null;
		//分页处理
		Integer start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(pageSize);
		Integer size = Integer.parseInt(pageSize);
		if ("1".equals(timeType)) {
		 objList = commentNaichaDao.getCommentNaichaToday(start,size);
		}else
		if ("2".equals(timeType)) {
			 objList = commentNaichaDao.getCommentNaichaThisWeek(start,size);
		} else
		if ("3".equals(timeType)) {
			 objList = commentNaichaDao.getCommentNaichaThisMonth(start,size);
		} else{
			return new ArrayList<RespComment2Naicha>();
		}
		List<RespComment2Naicha> respComment2NaichaList =  new ArrayList<RespComment2Naicha>();
		for (Object[] obj : objList) {
			//c.rank,c.content,uu.name beCommentName,u.`name` toCommentName,c.time
			RespComment2Naicha respComment2Naicha =  new RespComment2Naicha();
			respComment2Naicha.setRank((Integer) obj[0]);
			respComment2Naicha.setContent((String) obj[1]);
			respComment2Naicha.setBeCommentName((String) obj[2]);
			respComment2Naicha.setToCommentName((String) obj[3]);
			respComment2Naicha.setTime((Date) obj[4]);
			respComment2NaichaList.add(respComment2Naicha);
		}
		return respComment2NaichaList;
	}
}
