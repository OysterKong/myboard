package com.oyster.myboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.oyster.myboard.domain.ReplyDto;

public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired
	private SqlSession session;
	private static String namespace="com.oyster.myboard.mappers.reply.ReplyMapper.";

	
	
	@Override
	public List<ReplyDto> list(Integer article_no) throws Exception {
		return session.selectList(namespace + "list", article_no);
	}
	
	
	@Override
	public void create(ReplyDto dto) throws Exception {
		session.insert(namespace + "create", dto);
	}
	
	
	@Override
	public void update(ReplyDto dto) throws Exception {
		session.update(namespace + "update", dto);
	}
	
	
	@Override
	public void delete(Integer reply_no) throws Exception {
		session.delete(namespace + "delete", reply_no);
	}
	
	
	
}
