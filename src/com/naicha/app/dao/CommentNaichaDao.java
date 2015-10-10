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
	
	@Query(nativeQuery=true,value="select rank,count(rank)from comment_naicha  where beCommentId=?1 GROUP BY rank desc ")
	public List<Object[]> findRankCount(Integer userId);
	
	@Query(nativeQuery=true,value="select a.rank,case when b.rankcount is null then 0 ELSE b.rankcount end from comment_naicha a left JOIN(SELECT rank, count(rank) rankcount from comment_naicha where to_days(time) = to_days(now())  GROUP BY rank DESC)b on a.rank=b.rank   GROUP BY a.rank desc ")
	public List<Object[]> findRankCountToday();
	
	@Query(nativeQuery=true,value="select a.rank,case when b.rankcount is null then 0 ELSE b.rankcount end from comment_naicha a left JOIN(SELECT rank, count(rank) rankcount from comment_naicha where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(time)  GROUP BY rank DESC)b on a.rank=b.rank   GROUP BY a.rank desc   ")
	public List<Object[]> findRankCountThisWeek();
	
	@Query(nativeQuery=true,value="select a.rank,case when b.rankcount is null then 0 ELSE b.rankcount end from comment_naicha a left JOIN(SELECT rank, count(rank) rankcount from comment_naicha where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(time) GROUP BY rank DESC)b on a.rank=b.rank   GROUP BY a.rank desc  ")
	public List<Object[]> findRankCountThisMonth();

	/**
	 * 查询当天的评价
	 * @author yangxujia
	 * @param size 
	 * @param start 
	 * @date 2015年10月7日下午2:42:43
	 */
	@Query(nativeQuery=true,value="select c.rank,c.content,uu.name beCommentName,u.`name` toCommentName,c.time from comment_naicha c LEFT JOIN user u on toCommentId=u.id LEFT JOIN user uu on beCommentId = uu.id where to_days(time) = to_days(now()) ORDER BY c.time desc limit ?1 ,?2  ")
	public List<Object[]> getCommentNaichaToday(int start, int size);

	/**
	 * 查询本周的评价
	 * @author yangxujia
	 * @date 2015年10月7日下午2:43:00
	 */
	@Query(nativeQuery=true,value="select c.rank,c.content,uu.name beCommentName,u.`name` toCommentName,c.time from comment_naicha c LEFT JOIN user u on toCommentId=u.id LEFT JOIN user uu on beCommentId = uu.id  where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(time) ORDER BY c.time desc limit ?1 ,?2   ")
	public List<Object[]> getCommentNaichaThisWeek(int start, int size);
	
	/**
	 * 查询本月的评价
	 * @author yangxujia
	 * @date 2015年10月7日下午2:43:14
	 */
	@Query(nativeQuery=true,value=" select c.rank,c.content,uu.name beCommentName,u.`name` toCommentName,c.time from comment_naicha c LEFT JOIN user u on toCommentId=u.id LEFT JOIN user uu on beCommentId = uu.id where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(time) ORDER BY c.time desc limit ?1 ,?2   ")
	public List<Object[]> getCommentNaichaThisMonth(int start, int size);
}
