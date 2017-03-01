package com.lz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.lz.entity.UserInfo;

@Component
@SuppressWarnings("unchecked")
public class UserInfoDao {

	@Resource
	protected HibernateTemplate hibernateTemplate;
	
	public void saveUserInfo(UserInfo userInfo){
		
		hibernateTemplate.save(userInfo);
	}
	
	public void updateUserInfo(UserInfo userInfo) {

		hibernateTemplate.update(userInfo);
	}

	public void deleteUserInfo(UserInfo userInfo) {

		hibernateTemplate.delete(userInfo);
	}

	public List<UserInfo> listUserInfoBySql(String sql) {

		return this.hibernateTemplate.find(sql);
	}
}
