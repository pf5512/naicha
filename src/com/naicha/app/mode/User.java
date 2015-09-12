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
@Table(name = "user")
public class User implements Serializable {

	/**
	 * @author yangxujia
	 * @date 2015-9-8上午11:01:23
	 */
	private static final long serialVersionUID = 5926828635366965613L;
	private Integer id;
	private String headPicture;
	private String name;
	private String password;
	private Integer age;
	private String profession;
	private String address;
	private String coordinate;
	private String phone;
	private Integer userType;
	private Date registerTime;
	private Boolean isActivate;
	private Integer rank;
	private String naichaNo;
	private String perSignature;
	private String weiXinNo;
	private Integer sex;
	private Date birthday;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "headPicture", nullable = false)
	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}


	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age", nullable = false)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "profession", nullable = false)
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "coordinate", nullable = false)
	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	@Column(name = "phone", nullable = false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "userType", nullable = false)
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Column(name = "regitsterTime", nullable = false)
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "isActivate", nullable = false)
	public Boolean getIsActivate() {
		return isActivate;
	}

	public void setIsActivate(Boolean isActivate) {
		this.isActivate = isActivate;
	}

	@Column(name = "rank", nullable = false)
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "naichaNo", nullable = false)
	public String getNaichaNo() {
		return naichaNo;
	}

	public void setNaichaNo(String naichaNo) {
		this.naichaNo = naichaNo;
	}

	@Column(name = "perSignature", nullable = false)
	public String getPerSignature() {
		return perSignature;
	}

	public void setPerSignature(String perSignature) {
		this.perSignature = perSignature;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "sex", nullable = false)
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "birthday", nullable = false)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "weiChatNo", nullable = false)
	public String getWeiXinNo() {
		return weiXinNo;
	}

	public void setWeiXinNo(String weiXinNo) {
		this.weiXinNo = weiXinNo;
	}


}
