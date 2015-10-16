package com.naicha.app.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_naicha")
public class CommentNaicha {
	private Integer id;
	private Integer rank;
	private String content;
	private Integer beCommentId;
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

	@Column(name = "rank", nullable = false)
	public Integer getRank() {
		return rank==null?0:rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return content==null?"":content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "beCommentId", nullable = false)
	public Integer getBeCommentId() {
		return beCommentId;
	}

	public void setBeCommentId(Integer beCommentId) {
		this.beCommentId = beCommentId;
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
