package com.naicha.app.mode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jspxcms.core.service.CommentService;

@Entity
@Table(name = "friendCircle")
public class FriendCircle implements Serializable {

	/**
	 * @author yangxujia
	 * @date 2015年9月12日下午4:17:44
	 */
	private static final long serialVersionUID = 8884122570799262374L;
	private Integer id;
	private Integer userId;
	private String location;
	private String jinwei;
	private String content;
	private Date time;
	private User user;//带出用户信息
	private List<Pictures> picturesList;//带出朋友圈图片列表
	private List<Praise> praiseListList;//带出赞的列表
	private List<CommentFriendCircle> commentList;//带出评论

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId", nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "location", nullable = false)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "jinwei", nullable = false)
	public String getJinwei() {
		return jinwei;
	}

	public void setJinwei(String jinwei) {
		this.jinwei = jinwei;
	}
	
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", nullable = false)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Transient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public List<Pictures> getPicturesList() {
		return picturesList;
	}

	public void setPicturesList(List<Pictures> picturesList) {
		this.picturesList = picturesList;
	}

	@Transient
	public List<Praise> getPraiseListList() {
		return praiseListList;
	}

	public void setPraiseListList(List<Praise> praiseListList) {
		this.praiseListList = praiseListList;
	}

	@Transient
	public List<CommentFriendCircle> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentFriendCircle> commentList) {
		this.commentList = commentList;
	}

}
