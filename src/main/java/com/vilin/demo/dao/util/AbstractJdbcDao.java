package com.vilin.demo.dao.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public class AbstractJdbcDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Long getLastId() {
		return jdbcTemplate.queryForObject("select last_insert_id() as id", Long.class);
	}
	
	public <T> T queryForObject(String sql, Class<T> clazz, Object... args){
		Assert.hasText(sql, "sql语句不能为空！");
		return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<T>(clazz));
	}
	
	public <T> List<T> queryForObjectList(String sql, Class<T> clazz, Object... args){
		Assert.hasText(sql, "sql语句不能为空！");
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(clazz));
	}
	
	public Page<Map<String, Object>> queryForPage(String sql, int pageCurrent, int pageSize, Object... args){
		Assert.hasText(sql, "sql语句不能为空");
		Assert.isTrue(pageCurrent >= 1, "查询页数大于等于1");
		String sqlCount = Sql.countSql(sql);
		int count = jdbcTemplate.queryForObject(sqlCount, Integer.class, args);//条件查询下的总记录数
		pageCurrent = Sql.checkPageCurrent(count, pageSize, pageCurrent);//当前页
		pageSize = Sql.checkPageSize(pageSize);//每页显示的记录数
		int totalPage = Sql.countTotalPage(count, pageSize);//条件查询下的总页数
		String sqlList = sql + Sql.limitSql(count, pageCurrent, pageSize);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlList, args);
		return new Page<Map<String, Object>>(count, totalPage, pageCurrent, pageSize, list);
	}
	
	public <T> Page<T> queryForPage(String sql, int pageCurrent, int pageSize, Class<T> clazz, Object... args){
		Assert.hasText(sql, "sql语句不能为空");
		Assert.isTrue(pageCurrent >= 1, "查询页数大于等于1");
		Assert.isTrue(clazz != null, "calzz不能为空");
		String sqlCount = Sql.countSql(sql);
		System.out.println(sqlCount);
		int count = jdbcTemplate.queryForObject(sqlCount, Integer.class, args);//条件查询下的总记录数
		pageCurrent = Sql.checkPageCurrent(count, pageSize, pageCurrent);//当前页
		pageSize = Sql.checkPageSize(pageSize);//每页显示的记录数
		int totalPage = Sql.countTotalPage(count, pageSize);//条件查询下的总页数
		String sqlList = sql + Sql.limitSql(count, pageCurrent, pageSize);
		System.out.println(sqlList);
		List<T> list = jdbcTemplate.query(sqlList, new BeanPropertyRowMapper<T>(clazz), args);
		return new Page<T>(count, totalPage, pageCurrent, pageSize, list);
	}
}
