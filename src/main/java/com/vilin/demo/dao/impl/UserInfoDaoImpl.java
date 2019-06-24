package com.vilin.demo.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vilin.demo.bean.UserInfo;
import com.vilin.demo.dao.UserInfoDao;
import com.vilin.demo.dao.util.AbstractJdbcDao;
import com.vilin.demo.dao.util.Page;

@Repository
public class UserInfoImpl extends AbstractJdbcDao implements UserInfoDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public int insertUserInfo(UserInfo userInfo) {
		String sql = "insert into userinfo(name) values(?)";
		return template.update(sql, userInfo.getName());
	}

	@Override
	public int deleteUserInfo(UserInfo userInfo) {
		String sql = "delete from userinfo where id=?";
		return template.update(sql, userInfo.getId());
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		String sql = "update userinfo set name=? where id=?";
		return template.update(sql, userInfo.getName(), userInfo.getId());
	}

	@Override
	public UserInfo getUserInfo(UserInfo userInfo) {
		String sql = "select * from userinfo where id=?";
//		return template.queryForObject(sql, new RowMapper<UserInfo>() {
//
//			@Override
//			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				UserInfo userInfo = new UserInfo();
//				userInfo.setId(rs.getInt(1));
//				userInfo.setName(rs.getString(2));
//				return userInfo;
//			}
//		}, userInfo.getId());
		return queryForObject(sql, UserInfo.class, userInfo.getId());
	}

	@Override
	public Page<Map<String, Object>> queryForPage(int pageCurrent, int pageSize, UserInfo userInfo) {
		String sql = "select * from userInfo where name=?";
		return queryForPage(sql, pageCurrent, pageSize, userInfo.getName());
	}
	
	@Override
	public Page<UserInfo> queryForPage2(int pageCurrent, int pageSize, UserInfo userInfo) {
		String sql = "select * from userInfo where name=?";
		return queryForPage(sql, pageCurrent, pageSize, UserInfo.class, userInfo.getName());
	}
	
}
