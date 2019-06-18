package com.vilin.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vilin.demo.bean.UserInfo;

@RestController
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/test")
	public String index() {
		return "hello spring boot";
	}
	
	@RequestMapping("find/{id}/{name}")
	public UserInfo find(@PathVariable Integer id, @PathVariable String name) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setName(name);
		return userInfo;
	}
}
