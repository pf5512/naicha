package com.sihu.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihu.app.dao.CommentDao;
import com.sihu.app.mode.Comment;
import com.sihu.app.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public Comment save(Comment comment) {
		return commentDao.save(comment);
	}

	@Override
	public List<Comment> findBySportId(int sportId) {
		List<Object[]> listObject = commentDao.findBySportId(sportId);
		List<Comment> listComment = new ArrayList<Comment>();
		for (Object[] objects : listObject) {
			Comment comment = new Comment();
			comment = this.convert(objects);
			listComment.add(comment);
		}
		return listComment;
	}
	
	private Comment convert(Object[] objects){
		Comment comment = new Comment();
		comment.setId((Integer) objects[0]);
		comment.setUserId((Integer) objects[1]);
		comment.setSportId((Integer) objects[2]);
		comment.setContent((String) objects[3]);
		comment.setPid((Integer) objects[4]);
		comment.setTime((Date) objects[5]);
		comment.setName((String) objects[6]);
		comment.setHeadPicture((String) objects[7]);
		return comment;
	}

	@Override
	public List<Comment> findByInterestId(int interestId) {

		List<Object[]> listObject = commentDao.findByInterestId(interestId);
		List<Comment> listComment = new ArrayList<Comment>();
		for (Object[] objects : listObject) {
			Comment comment = new Comment();
			comment = this.convert(objects);
			listComment.add(comment);
		}
		return listComment;
	}
}
 