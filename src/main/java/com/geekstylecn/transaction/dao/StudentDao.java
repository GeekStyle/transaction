package com.geekstylecn.transaction.dao;

import com.geekstylecn.transaction.model.Student;

public interface StudentDao {
	
	public void insertStudent(Student student);
	
	public void deleteStudent(Long id);
	
}
