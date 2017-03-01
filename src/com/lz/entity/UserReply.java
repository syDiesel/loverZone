package com.lz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserReply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_reply", catalog = "loverzone")
public class UserReply implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserQuestion userQuestion;
	private UserInfo userInfoByReplyid;
	private UserInfo userInfoByUserId;
	private String replyTime;
	private String replySubject;
	private String replyContent;
	private String isRead;

	// Constructors

	/** default constructor */
	public UserReply() {
	}

	/** full constructor */
	public UserReply(UserQuestion userQuestion, UserInfo userInfoByReplyid,
			UserInfo userInfoByUserId, String replyTime, String replySubject,
			String replyContent, String isRead) {
		this.userQuestion = userQuestion;
		this.userInfoByReplyid = userInfoByReplyid;
		this.userInfoByUserId = userInfoByUserId;
		this.replyTime = replyTime;
		this.replySubject = replySubject;
		this.replyContent = replyContent;
		this.isRead = isRead;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionId")
	public UserQuestion getUserQuestion() {
		return this.userQuestion;
	}

	public void setUserQuestion(UserQuestion userQuestion) {
		this.userQuestion = userQuestion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "replyid")
	public UserInfo getUserInfoByReplyid() {
		return this.userInfoByReplyid;
	}

	public void setUserInfoByReplyid(UserInfo userInfoByReplyid) {
		this.userInfoByReplyid = userInfoByReplyid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public UserInfo getUserInfoByUserId() {
		return this.userInfoByUserId;
	}

	public void setUserInfoByUserId(UserInfo userInfoByUserId) {
		this.userInfoByUserId = userInfoByUserId;
	}

	@Column(name = "replyTime", length = 200)
	public String getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	@Column(name = "replySubject", length = 200)
	public String getReplySubject() {
		return this.replySubject;
	}

	public void setReplySubject(String replySubject) {
		this.replySubject = replySubject;
	}

	@Column(name = "replyContent", length = 65535)
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "isRead", length = 10)
	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

}