package com.oyster.myboard.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oyster.myboard.domain.LoginDto;
import com.oyster.myboard.domain.UserDto;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession session;
	private static String namespace="com.oyster.myboard.mappers.user.UserMapper.";
	
	
	@Override
	public void register(UserDto dto) throws Exception {
		session.insert(namespace + "register", dto);
	}
	
	@Override
	public UserDto login(LoginDto loginDto) throws Exception {
		return session.selectOne(namespace + "login", loginDto);
	}
	
	//로그인 유지 처리 담당
	@Override
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("sessionId", sessionId);
		paramMap.put("sessionLimit", sessionLimit);
		
		session.update(namespace + "keepLogin", paramMap);
	}
	
	//세션키 검증 담당
	@Override
	public UserDto checkUserWithSessionKey(String value) throws Exception {
		return session.selectOne(namespace + "checkUserWithSessionKey", value);
	}

}
