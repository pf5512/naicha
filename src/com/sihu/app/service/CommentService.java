package com.sihu.app.service;

import java.util.List;

import com.sihu.app.mode.Comment;

public interface CommentService {

	/**
	 * 保存评论
	 * @author yangxujia
	 * @date 2015-1-22下午4:33:55
	 * @param comment
	 * @return
	 */
	public Comment save(Comment comment);
	
	public List<Comment> findBySportId(int sportId);

	public List<Comment> findByInterestId(int interestId);
}
