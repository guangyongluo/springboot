package com.vilin.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vilin.demo.bean.UserInfo;
import com.vilin.demo.dao.UserInfoDao;
import com.vilin.demo.dao.util.Page;

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
}
