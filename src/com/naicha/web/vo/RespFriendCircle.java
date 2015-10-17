package com.naicha.web.vo;

import java.util.List;

import com.naicha.app.mode.CommentFriendCircle;
import com.naicha.app.mode.Pictures;
import com.naicha.app.mode.Praise;


public class RespFriendCircle {
	private Integer id;
	private String headPicture;
	private String name;
	private Integer sex;
	private String address;
	private Double distance;//根据坐标计算所得
	private String content;//内容
	private String time;
	private Integer userId;
	private List<Pictures> pictursList;
	private Integer picNum;
	private List<Praise> praiseList;
	private List<CommentFriendCircle> commentFriendCircleList;
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
	public Integer getSex() {
		return sex==null?-1:sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address==null?"":address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content==null?"":content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time==null?"":time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Pictures> getPictursList() {
		return pictursList;
	}
	public void setPictursList(List<Pictures> pictursList) {
		this.pictursList = pictursList;
	}
	public List<Praise> getPraiseList() {
		return praiseList;
	}
	public void setPraiseList(List<Praise> praiseList) {
		this.praiseList = praiseList;
	}
	public List<CommentFriendCircle> getCommentFriendCircleList() {
		return commentFriendCircleList;
	}
	public void setCommentFriendCircleList(
			List<CommentFriendCircle> commentFriendCircleList) {
		this.commentFriendCircleList = commentFriendCircleList;
	}
	public Double getDistance() {
		return distance==null?0:distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPicNum() {
		return picNum==null?0: picNum;
	}
	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}
}
