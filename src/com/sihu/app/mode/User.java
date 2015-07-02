package com.sihu.app.mode;

import java.io.Serializable;
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
@Table(name = "user")
public class User implements Serializable {
	/**
	 * @author yangxujia
	 * @date 2014-12-19下午6:56:56
	 */
	private static final long serialVersionUID = -3979990771413901512L;

	private int id;
	private String username;
	private String password;
	private String sid;
	private int age;
	private String phone;
	private double xx;
	private double yy;
	private int validateState;
	private String headPicture;
	private Date registrantionTime;
	private String email;
	private String hometown;
	private int sex;
	private String hobby;
	private String personalNote;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "sid", nullable = false)	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name = "phone", nullable = false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "xx", nullable = false)
	public double getXx() {
		return xx;
	}

	public void setXx(double xx) {
		this.xx = xx;
	}

	@Column(name = "yy", nullable = false)
	public double getYy() {
		return yy;
	}

	public void setYy(double yy) {
		this.yy = yy;
	}


	@Column(name = "validateState", nullable = false)
	public int getValidateState() {
		return validateState;
	}

	public void setValidateState(int validateState) {
		this.validateState = validateState;
	}

	@Column(name = "headPicture", nullable = false)
	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@JsonSerialize(using=JsonDateSerializerYYYYMMDDHHMMSS.class)
	@Column(name = "registrantionTime", nullable = false)
	public Date getRegistrantionTime() {
		return registrantionTime;
	}

	public void setRegistrantionTime(Date registrantionTime) {
		this.registrantionTime = registrantionTime;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	} 

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "hometown", nullable = false)
	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "age", nullable = false)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "sex", nullable = false)
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(name = "hobby", nullable = false)
	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "personalNote", nullable = false)
	public String getPersonalNote() {
		return personalNote;
	}

	public void setPersonalNote(String personalNote) {
		this.personalNote = personalNote;
	}

	




}
