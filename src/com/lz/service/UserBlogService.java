package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.UserBlogDao;
import com.lz.entity.UserBlog;

@Service
public class UserBlogService {
	
	@Resource
	protected UserBlogDao userBlogDao;

	public void saveUserBlog(UserBlog userBlog) {

		userBlogDao.saveUserBlog(userBlog);
	}

	public void updateUserBlog(UserBlog userBlog) {

		userBlogDao.updateUserBlog(userBlog);
	}

	public void deleteUserBlog(UserBlog userBlog) {

		userBlogDao.deleteUserBlog(userBlog);
	}

	public List<UserBlog> listUserBlogBySql(String sql) {

		return userBlogDao.listUserBlogBySql(sql);
	}

}
