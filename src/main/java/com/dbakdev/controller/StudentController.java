package com.dbakdev.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbakdev.model.Student;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
	
	private static final List<Student> students = Arrays.asList(
			new Student(1, "James Bond"),
			new Student(2, "Dilip Parajuli"),
			new Student(3, "Roji Dhimal"));
	
	@GetMapping("{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return students.stream()
				.filter(student -> studentId.equals(student.getStudentId()))
				.findFirst()
				.orElseThrow(()-> new IllegalStateException("Student " + studentId + "does not exisst."));
	}
}
