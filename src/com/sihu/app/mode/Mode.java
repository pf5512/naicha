package com.sihu.app.mode;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sihu.utils.JsonDateSerializerYYYYMMDDHHMMSS;

public class Mode {
	private int id;
	private int userId;
	private String mode;
	private Date startTime;
	private Date endTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "uid", nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "uid", nullable = false)
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JsonSerialize(using=JsonDateSerializerYYYYMMDDHHMMSS.class)
	@Column(name = "uid", nullable = false)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JsonSerialize(using=JsonDateSerializerYYYYMMDDHHMMSS.class)
	@Column(name = "uid", nullable = false)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


}
