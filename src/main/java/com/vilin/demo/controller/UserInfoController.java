package com.vilin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vilin.demo.bean.UserInfo;
import com.vilin.demo.service.UserInfoService;

@Controller
@RequestMapping("/userinfo/")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("show")
	public String show(UserInfo userInfo, Model model) {
		userInfo = userInfoService.findById(userInfo.getId());
		model.addAttribute("user", userInfo);
		return "index";
	}
}
