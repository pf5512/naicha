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
@Table(name = "contacts")
public class Contacts implements Serializable {
	/**
	 * @author yangxujia
	 * @date 2015年9月26日下午4:24:06
	 */
	private static final long serialVersionUID = -4675835305378675455L;
	private Integer id;
	private Integer userAId;
	private Integer userBId;
	private Integer isFriend;
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

	@Column(name = "userAId", nullable = false)
	public Integer getUserAId() {
		return userAId;
	}

	public void setUserAId(Integer userAId) {
		this.userAId = userAId;
	}

	@Column(name = "userBId", nullable = false)
	public Integer getUserBId() {
		return userBId;
	}

	public void setUserBId(Integer userBId) {
		this.userBId = userBId;
	}

	@Column(name = "isFriend", nullable = false)
	public Integer getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Integer isFriend) {
		this.isFriend = isFriend;
	}

	@Column(name = "time", nullable = false)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
