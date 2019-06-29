package com.vilin.demo.jpa.dao;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vilin.demo.bean.UserInfo;

public interface UserInfoJpaDao extends JpaRepository<UserInfo, Integer> {

	@Query(value = "From UserInfo u where u.name like ?1")
	public List<UserInfo> findByName(String name);
	
	public List<UserInfo> findByNameAndSex(String name, String sex);
	
	public List<UserInfo> findByNameLike(String name);
	
	public Page<UserInfo> findByNameLike(String name, Pageable pageable);
	
	@Cacheable(cacheNames="test", key="#p0")
	@Query(value = "From UserInfo u where u.id=?1")
	public UserInfo findOneUserInfo(Integer id);
	
	@CachePut(cacheNames="test", key="#p0.id")
	public UserInfo save(UserInfo userInfo);
}
