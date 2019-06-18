package com.vilin.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.vilin.demo.controller.IndexController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	private MockMvc mvc;
	
	public void before() {
		mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}
	
	@Test
	public void contextLoads() {
		RequestBuilder requestBuilder = get("/index/test");
		try {
			mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string("hello spring boot"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
