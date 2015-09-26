package com.naicha.web.vo;

import java.util.Date;

public class RespCommentNaicha {
	private Integer rank;
	private String content;
	private Integer beCommentId;
	private Integer toCommentId;
	private Date time;
	private String name;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBeCommentId() {
		return beCommentId;
	}

	public void setBeCommentId(Integer beCommentId) {
		this.beCommentId = beCommentId;
	}

	public Integer getToCommentId() {
		return toCommentId;
	}

	public void setToCommentId(Integer toCommentId) {
		this.toCommentId = toCommentId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
