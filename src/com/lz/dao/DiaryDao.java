package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.Diary;


@Component
@SuppressWarnings("unchecked")
public class DiaryDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveDiary(Diary diary) {

		hibernateTemplate.save(diary);
	}

	public void updateDiary(Diary diary) {

		hibernateTemplate.update(diary);
	}

	public void deleteDiary(Diary diary) {

		hibernateTemplate.delete(diary);
	}

	public List<Diary> listDiaryBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
