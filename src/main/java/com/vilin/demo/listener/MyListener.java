package com.vilin.demo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
	    System.out.println("MyListener sessionCreated...");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

	}


}
