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
 * Diary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "diary", catalog = "loverzone")
public class Diary implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfo;
	private String diarySubject;
	private String diaryContent;
	private String diaryTime;
	private String diaryVisited;

	// Constructors

	/** default constructor */
	public Diary() {
	}

	/** full constructor */
	public Diary(UserInfo userInfo, String diarySubject, String diaryContent,
			String diaryTime, String diaryVisited) {
		this.userInfo = userInfo;
		this.diarySubject = diarySubject;
		this.diaryContent = diaryContent;
		this.diaryTime = diaryTime;
		this.diaryVisited = diaryVisited;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "use_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "DiarySubject", length = 200)
	public String getDiarySubject() {
		return this.diarySubject;
	}

	public void setDiarySubject(String diarySubject) {
		this.diarySubject = diarySubject;
	}

	@Column(name = "DiaryContent", length = 65535)
	public String getDiaryContent() {
		return this.diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	@Column(name = "DiaryTime", length = 200)
	public String getDiaryTime() {
		return this.diaryTime;
	}

	public void setDiaryTime(String diaryTime) {
		this.diaryTime = diaryTime;
	}

	@Column(name = "DiaryVisited", length = 200)
	public String getDiaryVisited() {
		return this.diaryVisited;
	}

	public void setDiaryVisited(String diaryVisited) {
		this.diaryVisited = diaryVisited;
	}

}