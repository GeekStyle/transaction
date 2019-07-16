package com.geekstylecn.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstylecn.transaction.model.Student;
import com.geekstylecn.transaction.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	
	@PostMapping
	public ResponseEntity<?> insertStudent(@RequestBody Student student) {
		studentService.insertStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
}
