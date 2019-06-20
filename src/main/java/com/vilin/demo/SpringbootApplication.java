package com.vilin.demo;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import com.vilin.demo.filter.MyFilter1;
import com.vilin.demo.filter.MyFilter2;
import com.vilin.demo.filter.MyFilter3;
import com.vilin.demo.listener.MyListener;
import com.vilin.demo.servlet.MyServlet1;
import com.vilin.demo.servlet.MyServlet2;



@SpringBootApplication
@ServletComponentScan
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

//  implements ServletContextInitializer
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		servletContext.addServlet("my1", MyServlet1.class).addMapping("/my1");
//		servletContext.addServlet("my2", MyServlet2.class).addMapping("/my2");
//		servletContext.addFilter("f1", MyFilter1.class).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "my1");
//		servletContext.addFilter("f2", MyFilter2.class).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "my1");
//		servletContext.addFilter("f3", MyFilter3.class).addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, "my1");
//		servletContext.addListener(MyListener.class);
//	}

//	@Bean
//	public ServletRegistrationBean getServelt1() {
//		return new ServletRegistrationBean(new MyServlet1(), "/my1");
//	}
//	
//	@Bean
//	public ServletRegistrationBean getServelt2() {
//		return new ServletRegistrationBean(new MyServlet2(), "/my2");
//	}
//	
//	@Bean
//	public FilterRegistrationBean getFilter1() {
//		return new FilterRegistrationBean(new MyFilter1(), getServelt1(), getServelt2());
//	}
//	
//	@Bean
//	public FilterRegistrationBean getFilter2() {
//		return new FilterRegistrationBean(new MyFilter2(), getServelt1(), getServelt2());
//	}
//	
//	@Bean
//	public FilterRegistrationBean getFilter3() {
//		return new FilterRegistrationBean(new MyFilter3(), getServelt1(), getServelt2());
//	}
//	
//	@Bean
//	public ServletListenerRegistrationBean<MyListener> getListener() {
//		return new ServletListenerRegistrationBean<MyListener>(new MyListener());
//	}
}
