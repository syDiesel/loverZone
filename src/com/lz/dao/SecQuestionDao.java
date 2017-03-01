package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.SecQuestion;


@Component
@SuppressWarnings("unchecked")
public class SecQuestionDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveSecQuestion(SecQuestion secQuestion) {

		hibernateTemplate.save(secQuestion);
	}

	public void updateSecQuestion(SecQuestion secQuestion) {

		hibernateTemplate.update(secQuestion);
	}

	public void deleteSecQuestion(SecQuestion secQuestion) {

		hibernateTemplate.delete(secQuestion);
	}

	public List<SecQuestion> listSecQuestionBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
