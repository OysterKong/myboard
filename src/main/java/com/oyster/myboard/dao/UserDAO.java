package com.oyster.myboard.dao;

import java.util.Date;

import com.oyster.myboard.domain.LoginDto;
import com.oyster.myboard.domain.UserDto;

public interface UserDAO {
	
	//회원 가입 처리
	void register(UserDto dto) throws Exception;

	//로그인 처리
	UserDto login(LoginDto loginDto) throws Exception;

	//로그인 유지 처리 담당
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;

	//세션키 검증 담당
	UserDto checkUserWithSessionKey(String value) throws Exception;

}