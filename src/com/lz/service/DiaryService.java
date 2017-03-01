package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.DiaryDao;
import com.lz.entity.Diary;

@Service
public class DiaryService {
	
	@Resource
	protected DiaryDao diaryDao;

	public void saveDiary(Diary diary) {

		diaryDao.saveDiary(diary);
	}

	public void updateDiary(Diary diary) {

		diaryDao.updateDiary(diary);
	}

	public void deleteDiary(Diary diary) {

		diaryDao.deleteDiary(diary);
	}

	public List<Diary> listDiaryBySql(String sql) {

		return diaryDao.listDiaryBySql(sql);
	}

}
