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
 * UserBlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_blog", catalog = "loverzone")
public class UserBlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserInfo userInfo;
	private String blogContent;
	private String blogTime;
	private String blogPic;
	private Integer praise;

	// Constructors

	/** default constructor */
	public UserBlog() {
	}

	/** full constructor */
	public UserBlog(UserInfo userInfo, String blogContent, String blogTime,
			String blogPic, Integer praise) {
		this.userInfo = userInfo;
		this.blogContent = blogContent;
		this.blogTime = blogTime;
		this.blogPic = blogPic;
		this.praise = praise;
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
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "blogContent", length = 65535)
	public String getBlogContent() {
		return this.blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	@Column(name = "blogTime", length = 200)
	public String getBlogTime() {
		return this.blogTime;
	}

	public void setBlogTime(String blogTime) {
		this.blogTime = blogTime;
	}

	@Column(name = "blogPic", length = 200)
	public String getBlogPic() {
		return this.blogPic;
	}

	public void setBlogPic(String blogPic) {
		this.blogPic = blogPic;
	}

	@Column(name = "praise")
	public Integer getPraise() {
		return this.praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

}