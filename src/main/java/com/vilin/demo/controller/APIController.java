package com.vilin.demo.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vilin.demo.bean.Student;

@RestController
@RequestMapping("/api")
public class APIController {

	@RequestMapping("get")
	public HashMap<String, Object> get(@RequestParam String name){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		Student s = new Student();
		s.setId(10001);
		s.setName("张三");
		map.put("s", s);
		return map;
	}
	
}
