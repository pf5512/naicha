package com.naicha.web.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.naicha.app.utils.JsonDateSerializer;

public class RespTask {
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
		return weixinNo==null?"":getWeixinNo();
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
}
