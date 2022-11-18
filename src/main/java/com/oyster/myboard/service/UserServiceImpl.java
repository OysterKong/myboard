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
		userDao.updateLoginDate(loginDto.getUserId());
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
	
	//회원정보 얻기
	@Override
	public UserDto getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
	
	//회원정보 수정
	@Override
	public void modifyUser(UserDto dto) throws Exception {
		userDao.updateUser(dto);
	}
	
	//회원비밀번호 수정처리
	@Override
	public void modifyPw(UserDto dto) throws Exception {
		userDao.updatePw(dto);
	}
	
	//회원탈퇴
	@Override
	public void withdraw(UserDto dto) throws Exception {
		userDao.withdraw(dto);
	}
	
	//아이디 중복검사
	@Override
	public int duplicationCheck(String userId) throws Exception {
		return userDao.duplicationCheck(userId);
	}
	
	
}
