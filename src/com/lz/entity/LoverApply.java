package com.lz.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LoverApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lover_apply", catalog = "loverzone")
public class LoverApply implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfoByLoverId;
	private UserInfo userInfoByApplyId;
	private String statue;

	// Constructors

	/** default constructor */
	public LoverApply() {
	}

	/** minimal constructor */
	public LoverApply(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public LoverApply(Integer id, UserInfo userInfoByLoverId,
			UserInfo userInfoByApplyId, String statue) {
		this.id = id;
		this.userInfoByLoverId = userInfoByLoverId;
		this.userInfoByApplyId = userInfoByApplyId;
		this.statue = statue;
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
	@JoinColumn(name = "lover_id")
	public UserInfo getUserInfoByLoverId() {
		return this.userInfoByLoverId;
	}

	public void setUserInfoByLoverId(UserInfo userInfoByLoverId) {
		this.userInfoByLoverId = userInfoByLoverId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "apply_id")
	public UserInfo getUserInfoByApplyId() {
		return this.userInfoByApplyId;
	}

	public void setUserInfoByApplyId(UserInfo userInfoByApplyId) {
		this.userInfoByApplyId = userInfoByApplyId;
	}

	@Column(name = "statue")
	public String getStatue() {
		return this.statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

}