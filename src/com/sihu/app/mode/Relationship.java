package com.sihu.app.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sihu.utils.JsonDateSerializerYYYYMMDDHHMMSS;

@Entity
@Table(name="relationship")
public class Relationship {
	private int id;
	private int user1_id;
	private int user2_id;
	private int state;
	private String message;
	private Date time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user1_id",  nullable = false)
	public int getUser1_id() {
		return user1_id;
	}

	public void setUser1_id(int user1_id) {
		this.user1_id = user1_id;
	}

	@Column(name = "user2_id", nullable = false)
	public int getUser2_id() {
		return user2_id;
	}

	public void setUser2_id(int user2_id) {
		this.user2_id = user2_id;
	}

	@Column(name = "state", nullable = false)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "message",  nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JsonSerialize(using=JsonDateSerializerYYYYMMDDHHMMSS.class)
	@Column(name = "time",  nullable = false)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
