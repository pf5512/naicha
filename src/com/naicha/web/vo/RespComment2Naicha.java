package com.naicha.web.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.naicha.app.utils.JsonDateSerializerMMDDHHmm;

public class RespComment2Naicha {
	private Integer rank;
	private String content;
	private String beCommentName;
	private String toCommentName;
	private Date time;

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

	@JsonSerialize(using=JsonDateSerializerMMDDHHmm.class) 
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getBeCommentName() {
		return beCommentName;
	}

	public void setBeCommentName(String beCommentName) {
		this.beCommentName = beCommentName;
	}

	public String getToCommentName() {
		return toCommentName;
	}

	public void setToCommentName(String toCommentName) {
		this.toCommentName = toCommentName;
	}

}
