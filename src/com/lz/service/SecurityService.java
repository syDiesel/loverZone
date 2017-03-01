package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.SecurityDao;
import com.lz.entity.Security;

@Service
public class SecurityService {
	
	@Resource
	protected SecurityDao securityDao;

	public void saveSecurity(Security security) {

		securityDao.saveSecurity(security);
	}

	public void updateSecurity(Security security) {

		securityDao.updateSecurity(security);
	}

	public void deleteSecurity(Security security) {

		securityDao.deleteSecurity(security);
	}

	public List<Security> listSecurityBySql(String sql) {

		return securityDao.listSecurityBySql(sql);
	}

}
