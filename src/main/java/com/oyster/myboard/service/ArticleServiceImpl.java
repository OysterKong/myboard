package com.oyster.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.dao.ArticleDAO;
import com.oyster.myboard.domain.ArticleDto;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDAO articleDao;
	
	
	@Override
	public void create(ArticleDto dto) throws Exception {
		articleDao.create(dto);
	}
	
	@Override
	public ArticleDto read(Integer article_no) throws Exception {
		return articleDao.read(article_no);
	}
	
	@Override
	public void update(ArticleDto dto) throws Exception {
		articleDao.update(dto);
	}
	
	@Override
	public void delete(Integer article_no) throws Exception {
		articleDao.delete(article_no);
	}
	
	@Override
	public List<ArticleDto> listAll() throws Exception {
		return articleDao.listAll();
	}
	
	@Override
	public List<ArticleDto> listPageStandard(PageStandard standard) throws Exception {
		return articleDao.listPageStandard(standard);
	}
	
	@Override
	public int countArticles(PageStandard standard) throws Exception {
	    return articleDao.countArticles(standard);
	}
	
	
	

}
