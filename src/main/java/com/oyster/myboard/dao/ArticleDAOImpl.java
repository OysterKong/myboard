package com.oyster.myboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oyster.myboard.domain.ArticleDto;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	
	@Autowired
	private SqlSession session;
	private static String namespace="com.oyster.myboard.mappers.article.ArticleMapper.";
	
	@Override
	public void create(ArticleDto dto) throws Exception {
		session.insert(namespace + "create", dto);
	}
	
	@Override
	public ArticleDto read(Integer article_no) throws Exception {
		return session.selectOne(namespace + "read", article_no);
	}
	
	@Override
	public void update(ArticleDto dto) throws Exception {
		session.update(namespace + "update", dto);
	}
	
	@Override
	public void delete(Integer article_no) throws Exception {
		session.delete(namespace + "delete", article_no);
	}
	
	@Override
	public List<ArticleDto> listAll() throws Exception {
		return session.selectList(namespace + "listAll");
	}
	

}
