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
 * UserPhoto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_photo", catalog = "loverzone")
public class UserPhoto implements java.io.Serializable {

	// Fields

	private Integer id;
	private UserAlbum userAlbum;
	private String photoName;
	private String photoTime;
	private String photoDesc;
	private String photo;
	private Set<UserAlbum> userAlbums = new HashSet<UserAlbum>(0);

	// Constructors

	/** default constructor */
	public UserPhoto() {
	}

	/** full constructor */
	public UserPhoto(UserAlbum userAlbum, String photoName, String photoTime,
			String photoDesc, String photo, Set<UserAlbum> userAlbums) {
		this.userAlbum = userAlbum;
		this.photoName = photoName;
		this.photoTime = photoTime;
		this.photoDesc = photoDesc;
		this.photo = photo;
		this.userAlbums = userAlbums;
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
	@JoinColumn(name = "albumId")
	public UserAlbum getUserAlbum() {
		return this.userAlbum;
	}

	public void setUserAlbum(UserAlbum userAlbum) {
		this.userAlbum = userAlbum;
	}

	@Column(name = "photoName", length = 200)
	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	@Column(name = "photoTime", length = 200)
	public String getPhotoTime() {
		return this.photoTime;
	}

	public void setPhotoTime(String photoTime) {
		this.photoTime = photoTime;
	}

	@Column(name = "photoDesc", length = 65535)
	public String getPhotoDesc() {
		return this.photoDesc;
	}

	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
	}

	@Column(name = "photo", length = 225)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userPhoto")
	public Set<UserAlbum> getUserAlbums() {
		return this.userAlbums;
	}

	public void setUserAlbums(Set<UserAlbum> userAlbums) {
		this.userAlbums = userAlbums;
	}

}