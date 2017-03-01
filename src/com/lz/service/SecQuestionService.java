package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.SecQuestionDao;
import com.lz.entity.SecQuestion;

@Service
public class SecQuestionService {
	
	@Resource
	protected SecQuestionDao secQuestionDao;

	public void saveSecQuestion(SecQuestion secQuestion) {

		secQuestionDao.saveSecQuestion(secQuestion);
	}

	public void updateSecQuestion(SecQuestion secQuestion) {

		secQuestionDao.updateSecQuestion(secQuestion);
	}

	public void deleteSecQuestion(SecQuestion secQuestion) {

		secQuestionDao.deleteSecQuestion(secQuestion);
	}

	public List<SecQuestion> listSecQuestionBySql(String sql) {

		return secQuestionDao.listSecQuestionBySql(sql);
	}

}
