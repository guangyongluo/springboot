package com.vilin.demo.dao;

import java.util.Map;

import com.vilin.demo.bean.UserInfo;
import com.vilin.demo.dao.util.Page;

public interface UserInfoDao {

	public int insertUserInfo(UserInfo userInfo);
	
	public int deleteUserInfo(UserInfo userInfo);
	
	public int updateUserInfo(UserInfo userInfo);
	
	public UserInfo getUserInfo(UserInfo userInfo);
	
	public Page<Map<String, Object>> queryForPage(int pageCurrent, int pageSize, UserInfo userInfo);
	
	public Page<UserInfo> queryForPage2(int pageCurrent, int pageSize, UserInfo userInfo);
}
