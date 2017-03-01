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
 * UserAlbum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_album", catalog = "loverzone")
public class UserAlbum implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserPhoto userPhoto;
	private UserInfo userInfo;
	private String albumName;
	private String albumPsw;
	private String albumTime;
	private String albumDesc;
	private Set<UserPhoto> userPhotos = new HashSet<UserPhoto>(0);

	// Constructors

	/** default constructor */
	public UserAlbum() {
	}

	/** full constructor */
	public UserAlbum(UserPhoto userPhoto, UserInfo userInfo, String albumName,
			String albumPsw, String albumTime, String albumDesc,
			Set<UserPhoto> userPhotos) {
		this.userPhoto = userPhoto;
		this.userInfo = userInfo;
		this.albumName = albumName;
		this.albumPsw = albumPsw;
		this.albumTime = albumTime;
		this.albumDesc = albumDesc;
		this.userPhotos = userPhotos;
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
	@JoinColumn(name = "cover")
	public UserPhoto getUserPhoto() {
		return this.userPhoto;
	}

	public void setUserPhoto(UserPhoto userPhoto) {
		this.userPhoto = userPhoto;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "use_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Column(name = "albumName", length = 200)
	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Column(name = "albumPsw", length = 200)
	public String getAlbumPsw() {
		return this.albumPsw;
	}

	public void setAlbumPsw(String albumPsw) {
		this.albumPsw = albumPsw;
	}

	@Column(name = "albumTime", length = 200)
	public String getAlbumTime() {
		return this.albumTime;
	}

	public void setAlbumTime(String albumTime) {
		this.albumTime = albumTime;
	}

	@Column(name = "albumDesc", length = 65535)
	public String getAlbumDesc() {
		return this.albumDesc;
	}

	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAlbum")
	public Set<UserPhoto> getUserPhotos() {
		return this.userPhotos;
	}

	public void setUserPhotos(Set<UserPhoto> userPhotos) {
		this.userPhotos = userPhotos;
	}

}