package com.naicha.app.mode;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.search.TopDocs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.naicha.app.utils.JsonDateSerializer;
import com.naicha.app.utils.JsonDateSerializerMMDDHHmm;

@Entity
@Table(name = "task")
public class Task implements Serializable {
	/**
	 * @author yangxujia
	 * @date 2015年9月17日上午10:32:29
	 */
	private static final long serialVersionUID = 7284346646889457057L;
	private Integer id;
	private Integer userId;
	private Integer taskType;
	private Integer reward;
	private Date servicesTime;
	private Integer timeLength;
	private Date publicTime;
	private String notes;
	private String location;
	private Integer status;
	private String relativeToCurrentTime;
	private String relativeToClosingTime;
	private String headPicture;
	private BigInteger signupCount ;
	private BigInteger isCollect;
	private String name;
	private Integer sex;
	private Integer age;
	private Integer totop;
	private Double distance;

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
		return userId==null? 0 : userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "taskType", nullable = false)
	public Integer getTaskType() {
		return taskType==null? 0 : taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	@Column(name = "reward", nullable = false)
	public Integer getReward() {
		return reward==null?0:reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	@JsonSerialize(using=JsonDateSerializerMMDDHHmm.class) 
	@Column(name = "servicesTime", nullable = false)
	public Date getServicesTime() {
		return servicesTime;
	}

	public void setServicesTime(Date servicesTime) {
		this.servicesTime = servicesTime;
	}

	@Column(name = "timeLength", nullable = false)
	public Integer getTimeLength() {
		return timeLength==null?0:timeLength;
	}

	public void setTimeLength(Integer timeLength) {
		this.timeLength = timeLength;
	}

	@JsonSerialize(using=JsonDateSerializerMMDDHHmm.class) 
	@Column(name = "publicTime", nullable = false)
	public Date getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}

	@Column(name = "notes")
	public String getNotes() {
		return notes==null?"":notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "location")
	public String getLocation() {
		return location==null?"":location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Transient
	public String getRelativeToCurrentTime() {
		return relativeToCurrentTime;
	}

	public void setRelativeToCurrentTime(String relativeToCurrentTime) {
		this.relativeToCurrentTime = relativeToCurrentTime;
	}

	@Transient
	public String getRelativeToClosingTime() {
		return relativeToClosingTime;
	}

	public void setRelativeToClosingTime(String relativeToClosingTime) {
		this.relativeToClosingTime = relativeToClosingTime;
	}

	@Transient
	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}

	@Transient
	public Number getSignupCount() {
		return signupCount==null ?0 :signupCount;
	}

	public void setSignupCount(BigInteger signupCount) {
		this.signupCount = signupCount;
	}

	@Transient
	public String getName() {
		return name==null?"":name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public Integer getSex() {
		return sex==null?-1:sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Transient
	public Integer getAge() {
		return age==null?0:age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Transient
	public Number getIsCollect() {
		return isCollect==null?0:isCollect;
	}

	public void setIsCollect(BigInteger isCollect) {
		this.isCollect = isCollect;
	}


	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public Integer getTotop() {
		return totop==null?0:totop;
	}

	public void setTotop(Integer totop) {
		this.totop = totop;
	}

	@Transient
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
