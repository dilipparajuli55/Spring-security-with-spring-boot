package com.dbakdev.controller;

import com.dbakdev.model.Student;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(10, "Harry Kane"),
            new Student(9, "Erling Haaland"),
            new Student(11, "Darwin Nunez"));

    @GetMapping
    public List<Student> getStudents(){
        List<Student> STUDENTS = students;
        return STUDENTS;
    }
    
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        System.out.println(studentId);
    }
    
    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable("studentId") int studentId,@RequestBody Student student) {
    	System.out.println(String.format("%s %s", studentId, student));
    }
}
