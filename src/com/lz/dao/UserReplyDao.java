package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.UserReply;


@Component
@SuppressWarnings("unchecked")
public class UserReplyDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveUserReply(UserReply userReply) {

		hibernateTemplate.save(userReply);
	}

	public void updateUserReply(UserReply userReply) {

		hibernateTemplate.update(userReply);
	}

	public void deleteUserReply(UserReply userReply) {

		hibernateTemplate.delete(userReply);
	}

	public List<UserReply> listUserReplyBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}
}
