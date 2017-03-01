package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.UserBlog;

@Component
@SuppressWarnings("unchecked")
public class UserBlogDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveUserBlog(UserBlog userBlog) {

		hibernateTemplate.save(userBlog);
	}

	public void updateUserBlog(UserBlog userBlog) {

		hibernateTemplate.update(userBlog);
	}

	public void deleteUserBlog(UserBlog userBlog) {

		hibernateTemplate.delete(userBlog);
	}

	public List<UserBlog> listUserBlogBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
