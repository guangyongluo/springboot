package com.vilin.demo.bean;

import java.io.Serializable;

public class User implements Serializable{

	private Integer id;
	private String name;
	private String sex;
	
	public User(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
