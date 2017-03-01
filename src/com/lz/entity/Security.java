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
 * Security entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "security", catalog = "loverzone")
public class Security implements java.io.Serializable {

	// Fields

	private Integer id;
	private SecQuestion secQuestion;
	private UserInfo userInfo;
	private String answer;

	// Constructors

	/** default constructor */
	public Security() {
	}

	/** full constructor */
	public Security(SecQuestion secQuestion, UserInfo userInfo, String answer) {
		this.secQuestion = secQuestion;
		this.userInfo = userInfo;
		this.answer = answer;
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
	@JoinColumn(name = "secQ_id")
	public SecQuestion getSecQuestion() {
		return this.secQuestion;
	}

	public void setSecQuestion(SecQuestion secQuestion) {
		this.secQuestion = secQuestion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "use_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "answer", length = 65535)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}