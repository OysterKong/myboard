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
	
	//회원정보 얻기
	UserDto getUser(String userId) throws Exception;
	
	//회원정보 수정
	void modifyUser(UserDto dto) throws Exception;
	
	//회원비밀번호 수정처리
	void modifyPw(UserDto dto) throws Exception;
	
	//회원탈퇴
	void withdraw(UserDto dto) throws Exception;
	
	//아이디 중복검사
	int duplicationCheck(String userId) throws Exception;

}