package com.oyster.myboard.dao;

import java.util.List;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.commons.paging.SearchCondition;
import com.oyster.myboard.domain.ArticleDto;

public interface ArticleDAO {

	void create(ArticleDto dto) throws Exception;

	ArticleDto read(Integer article_no) throws Exception;

	void update(ArticleDto dto) throws Exception;

	void delete(Integer article_no) throws Exception;

	List<ArticleDto> listAll() throws Exception;
	
	List<ArticleDto> listPaging(int page) throws Exception;
	
	List<ArticleDto> listPageStandard(PageStandard standard) throws Exception;
	
	int countArticles(PageStandard standard) throws Exception;
	
	List<ArticleDto> listSearch(SearchCondition searchCondition) throws Exception;

	int countSearchedArticles(SearchCondition searchCondition) throws Exception;

	void updateReplyCnt(Integer article_no, int amount) throws Exception;

	void updateViewCnt(Integer article_no) throws Exception;

	List<ArticleDto> userBoardList(String userId) throws Exception;


}