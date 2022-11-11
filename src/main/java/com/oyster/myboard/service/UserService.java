package com.oyster.myboard.service;

import java.util.Date;

import com.oyster.myboard.domain.LoginDto;
import com.oyster.myboard.domain.UserDto;

public interface UserService {
	
	//회원 가입 처리
	void register(UserDto dto) throws Exception;
	
	//로그인 처리
	UserDto login(LoginDto loginDto) throws Exception;

	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;

	UserDto checkLoginBefore(String value) throws Exception;

}