package com.geekstylecn.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geekstylecn.transaction.dao.LogDao;
import com.geekstylecn.transaction.model.Log;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	LogDao logDao;
	
	@Override
	@Transactional
	public void insertLog(Log log) {
		logDao.insertLog(log);
	}
	
}
