package com.naicha.app.mode;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "praise")
public class Praise {
	private Integer id;
	private Integer friendCircleId;
	private Integer toPraiseUserId;
	private Integer isPraise;
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

	@Column(name = "friendCircleId",  nullable = false)
	public Integer getFriendCircleId() {
		return friendCircleId;
	}

	public void setFriendCircleId(Integer friendCircleId) {
		this.friendCircleId = friendCircleId;
	}

	@Column(name = "toPraiseUserId")
	public Integer getToPraiseUserId() {
		return toPraiseUserId;
	}

	public void setToPraiseUserId(Integer toPraiseUserId) {
		this.toPraiseUserId = toPraiseUserId;
	}

	public Integer getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Integer isPraise) {
		this.isPraise = isPraise;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
