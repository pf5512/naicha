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
@Table(name = "pictures")
public class Pictures implements Serializable {


	/**
	 * @author yangxujia
	 * @date 2015年9月12日下午4:57:37
	 */
	private static final long serialVersionUID = 3341407542832386863L;
	private Integer id;
	private String path;
	private Integer friendCircleId;
	private int hight;
	private int width;
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

	@Column(name = "path", nullable = false)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "friendCircleId", nullable = false)
	public Integer getFriendCircleId() {
		return friendCircleId;
	}

	public void setFriendCircleId(Integer friendCircleId) {
		this.friendCircleId = friendCircleId;
	}

	@Column(name = "hight", nullable = false)
	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}

	@Column(name = "width", nullable = false)
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Column(name = "time", nullable = false)
	public void setTime(Date time) {
		this.time=time;
	}

	public Date getTime() {
		return time;
	}

}
