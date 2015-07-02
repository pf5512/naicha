package com.sihu.app.mode;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "sport")
public class Sport implements Serializable {
	/**
	 * @author yangxujia
	 * @date 2015-1-19上午2:51:52
	 */
	private static final long serialVersionUID = 3133487041089031096L;

	private int id;
	private String title;
	private String sports;
	private Date startTime;
	private Date endTime;
	private String address;
	private String description;
	private int userId;
	private Double xx;
	private Double yy;
	private Date publicTime;
	private int joinNum;
	private int clickCount;
	
	//以下数据不做映射
	private String headPicture;
	private double distance;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false)	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "sports", nullable = false)
	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	@Column(name = "startTime", nullable = false)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", nullable = false)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	@Column(name = "publicTime", nullable = false)
	public Date getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}

	@Column(name = "joinNum", nullable = false)
	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	@Column(name = "clickCount", nullable = false)
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
