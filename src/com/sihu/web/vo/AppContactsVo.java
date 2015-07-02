package com.sihu.web.vo;
/**
 * Build contacts return model for JSON
 * @author yangxujia
 * @date 2014年8月9日下午3:40:09
 */
public class AppContactsVo {
	private String sid;
	private int type;
	private String uName;
	private String headPicture;
	private int isFriend;
	private String personalNote;//added by yxj 2014-11-10
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getHeadPicture() {
		return headPicture;
	}
	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
	public int getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(int isFriend) {
		this.isFriend = isFriend;
	}
	
	//added by yxj 2014-11-10
	public String getPersonalNote() {
		return personalNote;
	}
	
	//added by yxj 2014-11-10
	public void setPersonalNote(String personalNote) {
		this.personalNote = personalNote;
	}


}
