package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.UserQuestion;


@Component
@SuppressWarnings("unchecked")
public class UserQuestionDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveUserQuestion(UserQuestion userQuestion) {

		hibernateTemplate.save(userQuestion);
	}

	public void updateUserQuestion(UserQuestion userQuestion) {

		hibernateTemplate.update(userQuestion);
	}

	public void deleteUserQuestion(UserQuestion userQuestion) {

		hibernateTemplate.delete(userQuestion);
	}

	public List<UserQuestion> listUserQuestionBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
