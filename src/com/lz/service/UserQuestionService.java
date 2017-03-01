package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.UserQuestionDao;
import com.lz.entity.UserQuestion;

@Service
public class UserQuestionService {
	
	@Resource
	protected UserQuestionDao userQuestionDao;

	public void saveUserQuestion(UserQuestion userQuestion) {

		userQuestionDao.saveUserQuestion(userQuestion);
	}

	public void updateUserQuestion(UserQuestion userQuestion) {

		userQuestionDao.updateUserQuestion(userQuestion);
	}

	public void deleteUserQuestion(UserQuestion userQuestion) {

		userQuestionDao.deleteUserQuestion(userQuestion);
	}

	public List<UserQuestion> listUserQuestionBySql(String sql) {

		return userQuestionDao.listUserQuestionBySql(sql);
	}

}
