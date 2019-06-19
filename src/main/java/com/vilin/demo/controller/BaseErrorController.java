package com.vilin.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping(value="error")
public class BaseErrorController implements ErrorController{

	private static final Logger LOG = LoggerFactory.getLogger(BaseErrorController.class);
	
	
	@Override
	public String getErrorPath() {
		LOG.info("出错了，进入自定义错误控制器");
		return "error/error";
	}

	//@RequestMapping
	public String error() {
		return getErrorPath();
	}
}
