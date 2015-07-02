package com.sihu.app.mode;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "interest")
public class Interest {
	private int id;
	private int userId;
	private String content;
	private Date time;
	private Double xx;
	private Double yy;
	private int clickCount;
	
	//以下不做映射
	private double distance;
	private String headPicture;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_id",  nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "content",  nullable = false)
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

	@Column(name = "xx", nullable = false)
	public Double getXx() {
		return xx;
	}

	public void setXx(Double xx) {
		this.xx = xx;
	}

	@Column(name = "yy", nullable = false)
	public Double getYy() {
		return yy;
	}

	public void setYy(Double yy) {
		this.yy = yy;
	}

	@Column(name = "click_count",nullable = false)
	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	@Transient
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Transient
	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
}
