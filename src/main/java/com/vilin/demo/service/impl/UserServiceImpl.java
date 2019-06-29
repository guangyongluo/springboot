package com.vilin.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vilin.demo.bean.User;
import com.vilin.demo.dao.UserDao;
import com.vilin.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(Integer id) {
		return userDao.findByDao(id);
	}

}
