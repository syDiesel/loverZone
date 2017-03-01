package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.UserAlbumDao;
import com.lz.entity.UserAlbum;

@Service
public class UserAlbumService {
	
	@Resource
	protected UserAlbumDao userAlbumDao;

	public void saveUserAlbum(UserAlbum userAlbum) {

		userAlbumDao.saveUserAlbum(userAlbum);
	}

	public void updateUserAlbum(UserAlbum userAlbum) {

		userAlbumDao.updateUserAlbum(userAlbum);
	}

	public void deleteUserAlbum(UserAlbum userAlbum) {

		userAlbumDao.deleteUserAlbum(userAlbum);
	}

	public List<UserAlbum> listUserAlbumBySql(String sql) {

		return userAlbumDao.listUserAlbumBySql(sql);
	}
}
