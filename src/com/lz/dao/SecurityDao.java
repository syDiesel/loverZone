package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.Security;


@Component
@SuppressWarnings("unchecked")
public class SecurityDao {
	@Resource
	protected HibernateTemplate hibernateTemplate;

	public void saveSecurity(Security security) {

		hibernateTemplate.save(security);
	}

	public void updateSecurity(Security security) {

		hibernateTemplate.update(security);
	}

	public void deleteSecurity(Security security) {

		hibernateTemplate.delete(security);
	}

	public List<Security> listSecurityBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}
}
