package com.naicha.app.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.naicha.app.mode.CommentNaicha;

public interface CommentNaichaDao extends Repository<CommentNaicha, Integer> {
	
	public CommentNaicha save(CommentNaicha commentNaicha);

	@Query(nativeQuery=true,value="select count(1) from comment_naicha where beCommentId=?1 ")
	public BigInteger getCommentCountByUserId(int id);

	@Query(nativeQuery=true,value="select c.rank,c.content,c.beCommentId,c.toCommentId,c.time,u.name from comment_naicha c LEFT JOIN user u on toCommentId=u.id where  beCommentId=?1  ")
	public List<Object[]> findCommentById(Integer userId);
	
	@Query(nativeQuery=true,value="select count(rank)from comment_naicha  where beCommentId=?1 GROUP BY rank desc ")
	public List<BigInteger> findRankCount(Integer userId);
}
