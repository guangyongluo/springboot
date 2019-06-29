package com.vilin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vilin.demo.bean.User;
import com.vilin.demo.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("show")
	public String show(User user, Model model) {
		user = userService.findById(user.getId());
		model.addAttribute("user", user);
		return "index";
	}
	
}
