package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.UserPhotoDao;
import com.lz.entity.UserPhoto;

@Service
public class UserPhotoService {
	
	@Resource
	protected UserPhotoDao userPhotoDao;

	public void saveUserPhoto(UserPhoto userPhoto) {

		userPhotoDao.saveUserPhoto(userPhoto);
	}

	public void updateUserPhoto(UserPhoto userPhoto) {

		userPhotoDao.updateUserPhoto(userPhoto);
	}

	public void deleteUserPhoto(UserPhoto userPhoto) {

		userPhotoDao.deleteUserPhoto(userPhoto);
	}

	public List<UserPhoto> listUserPhotoBySql(String sql) {

		return userPhotoDao.listUserPhotoBySql(sql);
	}

}
