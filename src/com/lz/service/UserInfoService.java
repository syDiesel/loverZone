package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.UserInfoDao;
import com.lz.entity.UserInfo;

@Service
public class UserInfoService {

	@Resource
	protected UserInfoDao userInfoDao;
	
	public void saveUserInfo(UserInfo userInfo) {

		userInfoDao.saveUserInfo(userInfo);
	}

	public void updateUserInfo(UserInfo userInfo) {

		userInfoDao.updateUserInfo(userInfo);
	}

	public void deleteUserInfo(UserInfo userInfo) {

		userInfoDao.deleteUserInfo(userInfo);
	}

	public List<UserInfo> listUserInfoBySql(String sql) {

		return userInfoDao.listUserInfoBySql(sql);
	}

}
