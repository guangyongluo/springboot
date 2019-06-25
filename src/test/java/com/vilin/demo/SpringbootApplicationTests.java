package com.vilin.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.vilin.demo.bean.UserInfo;
import com.vilin.demo.component.RedisComponent;
import com.vilin.demo.dao.UserInfoDao;
import com.vilin.demo.dao.util.Page;
import com.vilin.demo.jpa.dao.UserInfoJpaDao;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

//	private MockMvc mvc;
//	
//	public void before() {
//		mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
//	}
//	
//	@Test
//	public void contextLoads() {
//		RequestBuilder requestBuilder = get("/index/test");
//		try {
//			mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string("hello spring boot"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserInfoJpaDao userInfoJpaDao;
	
	@Autowired
	private RedisComponent redisComponent;
	
	private String addr = "127.0.0.1";
	
	private int port = 6379;
	
	@Test
    public void contextLoads() {
    	
    }
	
	@Test
	public void insertUserInfoTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("张三");
		int num = userInfoDao.insertUserInfo(userInfo);
		System.out.println("num = " + num);
	}
	
	@Test
	public void updateUserInfoTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfo.setName("李四");
		int num = userInfoDao.updateUserInfo(userInfo);
		System.out.println("num = " + num);
	}
	
	@Test
	public void getUserInfoTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfo = userInfoDao.getUserInfo(userInfo);
		System.out.println("userInfo.name = " + userInfo.getName());
	}
	
	@Test
	public void deleteUserInfoTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		int num = userInfoDao.deleteUserInfo(userInfo);
		System.out.println("num = " + num);
	}
	
	@Test
	public void queryPageTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("张三");
		Page<UserInfo> page = userInfoDao.queryForPage2(1, 2, userInfo);
		System.out.println("共有" + page.getTotalCount() + "条记录");
		System.out.println("当前是第" + page.getPageCurrent() + "页");
		System.out.println("共有" + page.getTotalPage() + "页");
		List<UserInfo> users = page.getList();
		for(UserInfo user : users) {
			System.out.println("user.id = " + user.getId());
			System.out.println("user.name = " + user.getName());
		}
	}
	
	@Test
	public void insertTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("张三");
		userInfo.setSex("男");
		userInfoJpaDao.save(userInfo);
	}
	
	@Test
	public void updateTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfo.setName("李四");
		userInfo.setSex("女");
		userInfoJpaDao.save(userInfo);
	}
	
	@Test
	public void deleteTest() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1);
		userInfoJpaDao.delete(userInfo);
	}
	
	@Test
	public void queryTest() {
		List<UserInfo> list = userInfoJpaDao.findByName("张三");
		for(UserInfo userInfo : list) {
			System.out.println("userInfo.id = " + userInfo.getId() + " userInfo.name = " + userInfo.getName() + " userInfo.sex = " + userInfo.getSex());
		}
	}
	
	@Test
	public void queryTest0() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(3);
		userInfo = userInfoJpaDao.getOne(userInfo.getId());
		System.out.println(userInfo.getSex());
	}
	
	@Test
	public void queryTest1() {
		List<UserInfo> list = userInfoJpaDao.findByName("%三%");
		for(UserInfo userInfo : list) {
			System.out.println("userInfo.id = " + userInfo.getId() + " userInfo.name = " + userInfo.getName() + " userInfo.sex = " + userInfo.getSex());
		}
	}
	
	@Test
	public void queryTest2() {
		List<UserInfo> list = userInfoJpaDao.findByNameAndSex("张三", "男");
		for(UserInfo userInfo : list) {
			System.out.println("userInfo.id = " + userInfo.getId() + " userInfo.name = " + userInfo.getName() + " userInfo.sex = " + userInfo.getSex());
		}
	}
	
	@Test
	public void queryTest3() {
		List<UserInfo> list = userInfoJpaDao.findByNameLike("%张%");
		for(UserInfo userInfo : list) {
			System.out.println("userInfo.id = " + userInfo.getId() + " userInfo.name = " + userInfo.getName() + " userInfo.sex = " + userInfo.getSex());
		}
	}
	
	@Test
	public void queryTest4() {
		Pageable pageable = PageRequest.of(0, 2);
		org.springframework.data.domain.Page<UserInfo> page = userInfoJpaDao.findByNameLike("%张%", pageable);
		
		for(UserInfo userInfo : page.getContent()) {
			System.out.println("userInfo.id = " + userInfo.getId() + " userInfo.name = " + userInfo.getName() + " userInfo.sex = " + userInfo.getSex());
		}
	}
	
	@Test
	public void redisTest() {
		Jedis jedis = new Jedis(addr, port);
		jedis.set("str", "hello word!");
		System.out.println(jedis.ping());
		String value = jedis.get("str");
		System.out.println("value = " + value);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		jedis.hmset("map", map);
		map = jedis.hgetAll("map");
		System.out.println(map.get("b"));
		
		jedis.lpush("li", "a","b","c");
		List<String> list = jedis.lrange("li", 0, 100);
		for(String s : list) {
			System.out.println(s);
		}
	}
	
	@Test
	public void redisSetTest() {
		redisComponent.set("com", "hello world!!!");
	}
	
	@Test
	public void redisGetTest() {
		redisComponent.get("com");
	}
}
