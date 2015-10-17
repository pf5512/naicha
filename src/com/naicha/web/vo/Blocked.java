package com.naicha.web.vo;

/**
 * 黑名单
 * @author yangxujia
 * @date 2015年10月17日下午3:31:52
 */
public class Blocked {
	private Integer id;
	private String headPicture;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
}
