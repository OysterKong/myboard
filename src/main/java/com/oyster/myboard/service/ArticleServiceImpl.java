package com.oyster.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyster.myboard.dao.ArticleDAO;
import com.oyster.myboard.domain.ArticleDto;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDAO articledao;
	
	
	@Override
	public void create(ArticleDto dto) throws Exception {
		articledao.create(dto);
	}
	
	@Override
	public ArticleDto read(Integer article_no) throws Exception {
		return articledao.read(article_no);
	}
	
	@Override
	public void update(ArticleDto dto) throws Exception {
		articledao.update(dto);
	}
	
	@Override
	public void delete(Integer article_no) throws Exception {
		articledao.delete(article_no);
	}
	
	@Override
	public List<ArticleDto> listAll() throws Exception {
		return articledao.listAll();
	}
	

}
