package com.vilin.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vilin.demo.bean.UserInfo;

@RestController
@RequestMapping("/index")
public class IndexController {

	@Value(value="${vilin.secret}")
	private String value;
	
	@Value(value="${vilin.address}")
	private String address;
	
	@RequestMapping("/test")
	public String index() {
		return "hello spring boot";
	}
	
	@RequestMapping("find/{id}/{name}")
	public UserInfo find(@RequestParam Integer id, @RequestParam String name) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		userInfo.setName(name);
		return userInfo;
	}
	
	@RequestMapping("get")
	public Map<String, Object> get(@RequestParam String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("value", value);
		map.put("address", address);
		return map;
	}
}
