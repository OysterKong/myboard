package com.oyster.myboard.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyster.myboard.dao.UserDAO;
import com.oyster.myboard.domain.LoginDto;
import com.oyster.myboard.domain.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public void register(UserDto dto) throws Exception {
		userDao.register(dto);
	}
	
	@Override
	public UserDto login(LoginDto loginDto) throws Exception {
		return userDao.login(loginDto);
	}

	@Override
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		userDao.keepLogin(userId, sessionId, sessionLimit);
	}

	@Override
	public UserDto checkLoginBefore(String value) throws Exception {
		return userDao.checkUserWithSessionKey(value);
	}
	

}
