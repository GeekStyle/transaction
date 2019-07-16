package com.geekstylecn.transaction.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.geekstylecn.transaction.dao.StudentDao;
import com.geekstylecn.transaction.model.Log;
import com.geekstylecn.transaction.model.Student;
import com.geekstylecn.transaction.util.JSONUtil;

@Service
public class StudentServiceImpl implements StudentService{
	
	Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	LogService logService;
	
	@Override
	@Transactional
	public Student insertStudent(Student student) {
		Long studentId = 0l;
		Long logId = 0l;
		
		try {
			
			//insert student
			student.setCreateTime(new Date());
			studentDao.insertStudent(student);
			studentId = student.getId();
			
			//remote insert log
			Log log = new Log();
			log.setLog(student.getName() + " inserted");
			log.setCreateTime(new Date());
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(JSONUtil.toJSONString(log), headers);
			String result = restTemplate.postForObject("http://localhost/log", entity, String.class);
			logId = Long.parseLong(result);
			System.out.println(result);
			
//			logService.insertLog(log);
			
			createException(); 
		} catch (Exception e) {
			
			//rollback remote data
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete("http://localhost/log/" + logId);
			
			//trigger local transaction rollback
			createException();
		}
		return student;
	}
	
	private void createException() {
		throw new RuntimeException("a test Exception has been throwed");
	}

	@Override
	public void deleteStudent(Long id) {
		studentDao.deleteStudent(id);
	}
	
}
