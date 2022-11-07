package com.oyster.myboard.service;

import java.util.List;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.domain.ArticleDto;

public interface ArticleService {

	void create(ArticleDto dto) throws Exception;

	ArticleDto read(Integer article_no) throws Exception;

	void update(ArticleDto dto) throws Exception;

	void delete(Integer article_no) throws Exception;

	List<ArticleDto> listAll() throws Exception;
	
	List<ArticleDto> listPageStandard(PageStandard standard) throws Exception;
	
	int countArticles(PageStandard standard) throws Exception;

}