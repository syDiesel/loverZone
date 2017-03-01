package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.LoverApplyDao;
import com.lz.entity.LoverApply;

@Service
public class LoverApplyService {
	
	@Resource
	protected LoverApplyDao loverApplyDao;

	public void saveLoverApply(LoverApply loverApply) {

		loverApplyDao.saveLoverApply(loverApply);
	}

	public void updateLoverApply(LoverApply loverApply) {

		loverApplyDao.updateLoverApply(loverApply);
	}

	public void deleteLoverApply(LoverApply loverApply) {

		loverApplyDao.deleteLoverApply(loverApply);
	}

	public List<LoverApply> listLoverApplyBySql(String sql) {

		return loverApplyDao.listLoverApplyBySql(sql);
	}

}
