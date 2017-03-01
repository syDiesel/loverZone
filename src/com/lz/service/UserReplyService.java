package com.lz.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lz.dao.UserReplyDao;
import com.lz.entity.UserReply;

@Service
public class UserReplyService {
	
	@Resource
	protected UserReplyDao userReplyDao;

	public void saveUserReply(UserReply userReply) {

		userReplyDao.saveUserReply(userReply);
	}

	public void updateUserReply(UserReply userReply) {

		userReplyDao.updateUserReply(userReply);
	}

	public void deleteUserReply(UserReply userReply) {

		userReplyDao.deleteUserReply(userReply);
	}

	public List<UserReply> listUserReplyBySql(String sql) {

		return userReplyDao.listUserReplyBySql(sql);
	}

}
