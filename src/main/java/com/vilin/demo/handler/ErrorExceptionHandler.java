package com.vilin.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ErrorExceptionHandler.class);
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(Exception e) {
		LOG.info("-----自定义异常处理(Exception)-----");
		ModelAndView m = new ModelAndView();
		m.addObject("vilinException", e.getMessage());
		m.setViewName("error/500");
		return m;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(RuntimeException e) {
		LOG.info("-----自定义异常处理(RuntimeException)-----");
		ModelAndView m = new ModelAndView();
		m.addObject("vilinException", e.getMessage());
		m.setViewName("error/500");
		return m;
	}
}
