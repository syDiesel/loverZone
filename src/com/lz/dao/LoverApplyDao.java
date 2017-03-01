package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.LoverApply;

@Component
@SuppressWarnings("unchecked")
public class LoverApplyDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveLoverApply(LoverApply loverApply) {

		hibernateTemplate.save(loverApply);
	}

	public void updateLoverApply(LoverApply loverApply) {

		hibernateTemplate.update(loverApply);
	}

	public void deleteLoverApply(LoverApply loverApply) {

		hibernateTemplate.delete(loverApply);
	}

	public List<LoverApply> listLoverApplyBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}

}
