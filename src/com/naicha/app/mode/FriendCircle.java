package com.naicha.app.mode;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
