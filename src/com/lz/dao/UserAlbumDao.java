package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.UserAlbum;

@Component
@SuppressWarnings("unchecked")
public class UserAlbumDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveUserAlbum(UserAlbum userAlbum) {

		hibernateTemplate.save(userAlbum);
	}

	public void updateUserAlbum(UserAlbum userAlbum) {

		hibernateTemplate.update(userAlbum);
	}

	public void deleteUserAlbum(UserAlbum userAlbum) {

		hibernateTemplate.delete(userAlbum);
	}

	public List<UserAlbum> listUserAlbumBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
