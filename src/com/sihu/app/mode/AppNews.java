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

@Entity
@Table(name = "app_news")
public class AppNews implements Serializable{
	/**
	 * @author yangxujia
	 * @date 2014年8月14日下午4:31:03
	 */
	private static final long serialVersionUID = -8616885919005584033L;
	private int id;
	private int uid;
	private String sid;
	private String title;
	private String content;
	private int praiseCount;
	private int shareCount;
	private int commentCount;
	private Date releaseTime;
	private Date modifyTime;
	private int privatePublic;

	
	//xujian add
	private int isShare;//是否是分享 0：不是（默认） 1：是
	private String sourceUrl;//来源链接


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "uid", nullable = false)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "sid", nullable = false)
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "praise_count", nullable = false)
	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	@Column(name = "share_count", nullable = false)
	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	@Column(name = "comment_count", nullable = false)
	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}



	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@DateTimeFormat(pattern="yy-MM-dd HH:mm") 

	@Column(name = "modify_time", nullable = false)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

  
	@Column(name = "private_public", nullable = false)
	public int getPrivatePublic() {
		return privatePublic;
	}

	public void setPrivatePublic(int privatePublic) {
		this.privatePublic = privatePublic;
	}
	
}
