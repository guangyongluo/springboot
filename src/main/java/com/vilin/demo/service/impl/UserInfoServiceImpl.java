package com.vilin.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vilin.demo.bean.UserInfo;
import com.vilin.demo.jpa.dao.UserInfoJpaDao;
import com.vilin.demo.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoJpaDao userInfoJpaDao;
	
	@Cacheable(cacheNames="vilin")
	@Override
	public UserInfo findById(Integer id) {
		return userInfoJpaDao.findOneUserInfo(id);
	}
	
	public void update(UserInfo userInfo) {
		userInfoJpaDao.save(userInfo);
	}

}
