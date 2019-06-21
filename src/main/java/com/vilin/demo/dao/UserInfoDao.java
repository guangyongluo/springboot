package com.vilin.demo.dao;

import com.vilin.demo.bean.UserInfo;

public interface UserInfoDao {

	public int insertUserInfo(UserInfo userInfo);
	
	public int deleteUserInfo(UserInfo userInfo);
	
	public int updateUserInfo(UserInfo userInfo);
	
	public UserInfo getUserInfo(UserInfo userInfo);
	
}
