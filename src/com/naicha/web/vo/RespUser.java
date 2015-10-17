package com.naicha.web.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.naicha.app.mode.Pictures;
import com.naicha.app.utils.JsonDateSerializer;

public class RespUser {
	private Integer id;
	private String headPicture;
	private String name;
	private String age;
	private String profession;
	private String address;
	private String phone;
	private String userType;
	private Date regitsterTime;
	private String naichaNo;
	private String perSignature;
	private String weixinNo;
	private Integer sex;
	private Date birthday;
	private String rank;
	private Integer serviceType;
	private List<Pictures> picList;
	private Integer commentCount;
	private Integer isActive;
	private String	distance;
	
	public String getHeadPicture() {
		return headPicture==null?"":headPicture;
	}
	
	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
	
	public String getName() {
		return name==null?"":name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age==null?"18":age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getProfession() {
		return profession==null?"":profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAddress() {
		return address==null?"":address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone==null?"":phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType==null?"-1":userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getNaichaNo() {
		return naichaNo==null?"":naichaNo;
	}

	public void setNaichaNo(String naichaNo) {
		this.naichaNo = naichaNo;
	}

	public String getPerSignature() {
		return perSignature==null?"":perSignature;
	}

	public void setPerSignature(String perSignature) {
		this.perSignature = perSignature;
	}

	public Integer getSex() {
		return sex==null?-1:sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@JsonSerialize(using=JsonDateSerializer.class) 
	public Date getRegitsterTime() {
		return regitsterTime;
	}

	public void setRegitsterTime(Date regitsterTime) {
		this.regitsterTime = regitsterTime;
	}

	public String getWeixinNo() {
		return weixinNo;
	}

	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRank() {
		return rank==null?"":rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Integer getServiceType() {
		return serviceType==null?-1:serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public List<Pictures> getPicList() {
		return picList;
	}

	public void setPicList(List<Pictures> picList) {
		this.picList = picList;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getDistance() {
		return distance==null?"":distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
}
