package com.lz.util;

import com.lz.entity.UserInfo;

public class AuditModel {
	
	private long id;
	private String type;
	private String subject;
	private String content;
	private String time;
	private String blogPic;
	private UserInfo userInfo;
	
	
	public long getId() {
		return id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBlogPic() {
		return blogPic;
	}
	public void setBlogPic(String blogPic) {
		this.blogPic = blogPic;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	private int visit;

}
