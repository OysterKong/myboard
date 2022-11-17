package com.oyster.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.domain.ReplyDto;

@Repository
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
	
	@Override
	public List<ReplyDto> listPaging(Integer article_no, PageStandard standard) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("article_no", article_no);
		paramMap.put("standard", standard);
		
		return session.selectList(namespace + "listPaging", paramMap);
	}
	
	@Override
	public int countReplies(Integer article_no) throws Exception {
		return session.selectOne(namespace + "countReplies", article_no);
	}
	
	@Override
	public int getArticleNo(Integer reply_no) throws Exception {
	    return session.selectOne(namespace + "getArticleNo", reply_no);
	}
	
	//회원이 작성한 댓글 목록
	@Override
	public List<ReplyDto> userReplies(String userId) throws Exception {
		return session.selectList(namespace + "userReplies", userId);
	}
	
}
