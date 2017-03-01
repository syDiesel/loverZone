package com.lz.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UserQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_question", catalog = "loverzone")
public class UserQuestion implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfoByUseId;
	private UserInfo userInfoByBlogUser;
	private String questionTime;
	private String questionSubject;
	private String questionContent;
	private Set<UserReply> userReplies = new HashSet<UserReply>(0);

	// Constructors

	/** default constructor */
	public UserQuestion() {
	}

	/** full constructor */
	public UserQuestion(UserInfo userInfoByUseId, UserInfo userInfoByBlogUser,
			String questionTime, String questionSubject,
			String questionContent, Set<UserReply> userReplies) {
		this.userInfoByUseId = userInfoByUseId;
		this.userInfoByBlogUser = userInfoByBlogUser;
		this.questionTime = questionTime;
		this.questionSubject = questionSubject;
		this.questionContent = questionContent;
		this.userReplies = userReplies;
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
	@JoinColumn(name = "use_id")
	public UserInfo getUserInfoByUseId() {
		return this.userInfoByUseId;
	}

	public void setUserInfoByUseId(UserInfo userInfoByUseId) {
		this.userInfoByUseId = userInfoByUseId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blog_user")
	public UserInfo getUserInfoByBlogUser() {
		return this.userInfoByBlogUser;
	}

	public void setUserInfoByBlogUser(UserInfo userInfoByBlogUser) {
		this.userInfoByBlogUser = userInfoByBlogUser;
	}

	@Column(name = "questionTime", length = 200)
	public String getQuestionTime() {
		return this.questionTime;
	}

	public void setQuestionTime(String questionTime) {
		this.questionTime = questionTime;
	}

	@Column(name = "questionSubject", length = 200)
	public String getQuestionSubject() {
		return this.questionSubject;
	}

	public void setQuestionSubject(String questionSubject) {
		this.questionSubject = questionSubject;
	}

	@Column(name = "questionContent", length = 200)
	public String getQuestionContent() {
		return this.questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userQuestion")
	public Set<UserReply> getUserReplies() {
		return this.userReplies;
	}

	public void setUserReplies(Set<UserReply> userReplies) {
		this.userReplies = userReplies;
	}

}