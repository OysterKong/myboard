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
	
	//회원 정보 얻기
	UserDto getUser(String userId) throws Exception;
	
	//회원정보 수정
	void updateUser(UserDto dto) throws Exception;
	
	//회원비밀번호 수정
	void updatePw(UserDto dto) throws Exception;
	
	//회원 최근 로그인 일자 갱신
	void updateLoginDate(String userId) throws Exception;
	
	//회원탈퇴
	void withdraw(UserDto dto) throws Exception;
	
	//아이디 중복검사
	int duplicationCheck(String userId) throws Exception;

}