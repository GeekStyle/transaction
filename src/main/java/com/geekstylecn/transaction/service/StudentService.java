package com.geekstylecn.transaction.service;

import com.geekstylecn.transaction.model.Student;

public interface StudentService {
	
	public Student insertStudent(Student student);
	
	public void deleteStudent(Long id);
	
}
