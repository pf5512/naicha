package com.sihu.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.sihu.app.mode.Comment;

public interface CommentDao extends Repository<Comment, Integer> {
	public Comment save(Comment comment);

	/**
	 *  根据sportid查询评论
	 * @author yangxujia
	 * @date 2015-1-23上午3:11:24
	 * @param sportId
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT c.id,c.userId,c.sportId,c.content,c.pid,c.time,u.username,u.headPicture " +
			"from comment c left join user u on c.userId=u.id where c.sportId=?1")
	public List<Object[]> findBySportId(int sportId);

	@Query(nativeQuery = true, value = "SELECT c.id,c.userId,c.sportId,c.content,c.pid,c.time,u.username,u.headPicture " +
			"from comment c left join user u on c.userId=u.id where c.interestId=?1")
	public List<Object[]> findByInterestId(int interestId);
}
