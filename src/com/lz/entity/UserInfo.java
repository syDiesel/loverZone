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
import javax.persistence.Transient;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_info", catalog = "loverzone")
public class UserInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String passWord;
	private String sex;
	private String age;
	private String address;
	private String contact;
	private String homeName;
	private String homeDesc;
	private String headImg;
	private String email;
	private UserInfo userInfo;
	private Set<Diary> diaries = new HashSet<Diary>(0);
	private Set<UserInfo> userInfos = new HashSet<UserInfo>(0);
	private Set<UserAlbum> userAlbums = new HashSet<UserAlbum>(0);
	private Set<UserBlog> userBlogs = new HashSet<UserBlog>(0);
	private Set<LoverApply> loverAppliesForApplyId = new HashSet<LoverApply>(0);
	private Set<LoverApply> loverAppliesForLoverId = new HashSet<LoverApply>(0);
	private Set<UserQuestion> userQuestionsForBlogUser = new HashSet<UserQuestion>(
			0);
	private Set<UserReply> userRepliesForReplyid = new HashSet<UserReply>(0);
	private Set<Security> securities = new HashSet<Security>(0);
	private Set<UserQuestion> userQuestionsForUseId = new HashSet<UserQuestion>(
			0);
	private Set<UserReply> userRepliesForUserId = new HashSet<UserReply>(0);

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(UserInfo userInfo, String userName, String passWord,
			String sex, String age, String address, String contact,
			String homeName, String homeDesc, String headImg, String email,
			Set<Diary> diaries, Set<UserInfo> userInfos,
			Set<UserAlbum> userAlbums, Set<UserBlog> userBlogs,
			Set<LoverApply> loverAppliesForApplyId,
			Set<LoverApply> loverAppliesForLoverId,
			Set<UserQuestion> userQuestionsForBlogUser,
			Set<UserReply> userRepliesForReplyid, Set<Security> securities,
			Set<UserQuestion> userQuestionsForUseId,
			Set<UserReply> userRepliesForUserId) {
		this.userInfo = userInfo;
		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
		this.age = age;
		this.address = address;
		this.contact = contact;
		this.homeName = homeName;
		this.homeDesc = homeDesc;
		this.headImg = headImg;
		this.email = email;
		this.diaries = diaries;
		this.userInfos = userInfos;
		this.userAlbums = userAlbums;
		this.userBlogs = userBlogs;
		this.loverAppliesForApplyId = loverAppliesForApplyId;
		this.loverAppliesForLoverId = loverAppliesForLoverId;
		this.userQuestionsForBlogUser = userQuestionsForBlogUser;
		this.userRepliesForReplyid = userRepliesForReplyid;
		this.securities = securities;
		this.userQuestionsForUseId = userQuestionsForUseId;
		this.userRepliesForUserId = userRepliesForUserId;
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
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "userName", length = 40)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "passWord", length = 40)
	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "sex", length = 40)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "age", length = 40)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "address", length = 40)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "contact", length = 40)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "homeName", length = 40)
	public String getHomeName() {
		return this.homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	@Column(name = "homeDesc", length = 65535)
	public String getHomeDesc() {
		return this.homeDesc;
	}

	public void setHomeDesc(String homeDesc) {
		this.homeDesc = homeDesc;
	}

	@Column(name = "headImg", length = 225)
	public String getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	@Column(name = "email", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfo")
	public Set<Diary> getDiaries() {
		return this.diaries;
	}

	public void setDiaries(Set<Diary> diaries) {
		this.diaries = diaries;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfo")
	public Set<UserInfo> getUserInfos() {
		return this.userInfos;
	}

	public void setUserInfos(Set<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfo")
	public Set<UserAlbum> getUserAlbums() {
		return this.userAlbums;
	}

	public void setUserAlbums(Set<UserAlbum> userAlbums) {
		this.userAlbums = userAlbums;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfo")
	@Transient
	public Set<UserBlog> getUserBlogs() {
		return this.userBlogs;
	}

	public void setUserBlogs(Set<UserBlog> userBlogs) {
		this.userBlogs = userBlogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfoByApplyId")
	public Set<LoverApply> getLoverAppliesForApplyId() {
		return this.loverAppliesForApplyId;
	}

	public void setLoverAppliesForApplyId(Set<LoverApply> loverAppliesForApplyId) {
		this.loverAppliesForApplyId = loverAppliesForApplyId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfoByLoverId")
	public Set<LoverApply> getLoverAppliesForLoverId() {
		return this.loverAppliesForLoverId;
	}

	public void setLoverAppliesForLoverId(Set<LoverApply> loverAppliesForLoverId) {
		this.loverAppliesForLoverId = loverAppliesForLoverId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfoByBlogUser")
	@Transient
	public Set<UserQuestion> getUserQuestionsForBlogUser() {
		return this.userQuestionsForBlogUser;
	}

	public void setUserQuestionsForBlogUser(
			Set<UserQuestion> userQuestionsForBlogUser) {
		this.userQuestionsForBlogUser = userQuestionsForBlogUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfoByReplyid")
	@Transient
	public Set<UserReply> getUserRepliesForReplyid() {
		return this.userRepliesForReplyid;
	}

	public void setUserRepliesForReplyid(Set<UserReply> userRepliesForReplyid) {
		this.userRepliesForReplyid = userRepliesForReplyid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfo")
	public Set<Security> getSecurities() {
		return this.securities;
	}

	public void setSecurities(Set<Security> securities) {
		this.securities = securities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfoByUseId")
	public Set<UserQuestion> getUserQuestionsForUseId() {
		return this.userQuestionsForUseId;
	}

	public void setUserQuestionsForUseId(Set<UserQuestion> userQuestionsForUseId) {
		this.userQuestionsForUseId = userQuestionsForUseId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userInfoByUserId")
	public Set<UserReply> getUserRepliesForUserId() {
		return this.userRepliesForUserId;
	}

	public void setUserRepliesForUserId(Set<UserReply> userRepliesForUserId) {
		this.userRepliesForUserId = userRepliesForUserId;
	}

}