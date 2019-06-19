package com.vilin.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vilin.demo.bean.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping("show")
	public String show(Model model) {
		model.addAttribute("str","hello spring boot freemarker");
		Student student = new Student();
		student.setId(10001);
		student.setName("罗葳");
		model.addAttribute("stu", student);
		
		List<Student> list = new ArrayList<Student>();
		for(int i = 0; i < 3; i++) {
			Student stu = new Student();
			stu.setId(i);
			stu.setName("00000" + i);
			list.add(stu);
		}
		model.addAttribute("students", list);
		return "student";
	}
}
