package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.UserPhoto;


@Component
@SuppressWarnings("unchecked")
public class UserPhotoDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveUserPhoto(UserPhoto userPhoto) {

		hibernateTemplate.save(userPhoto);
	}

	public void updateUserPhoto(UserPhoto userPhoto) {

		hibernateTemplate.update(userPhoto);
	}

	public void deleteUserPhoto(UserPhoto userPhoto) {

		hibernateTemplate.delete(userPhoto);
	}

	public List<UserPhoto> listUserPhotoBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
