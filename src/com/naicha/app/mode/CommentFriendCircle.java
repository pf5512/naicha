package com.naicha.app.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_friendcircle")
public class CommentFriendCircle {
	private Integer id;
	private String content;
	private Integer friendCircleId;
	private Integer toCommentId;
	private Date time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return content==null?"":content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "friendCircleId", nullable = false)
	public Integer getFriendCircleId() {
		return friendCircleId;
	}

	public void setFriendCircleId(Integer friendCircleId) {
		this.friendCircleId = friendCircleId;
	}
	
	@Column(name = "toCommentId", nullable = false)
	public Integer getToCommentId() {
		return toCommentId;
	}

	public void setToCommentId(Integer toCommentId) {
		this.toCommentId = toCommentId;
	}

	@Column(name = "time", nullable = false)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
