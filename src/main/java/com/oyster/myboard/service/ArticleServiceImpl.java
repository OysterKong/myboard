package com.oyster.myboard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.commons.paging.SearchCondition;
import com.oyster.myboard.dao.ArticleDAO;
import com.oyster.myboard.dao.ArticleFileDAO;
import com.oyster.myboard.domain.ArticleDto;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	
	@Autowired
	private ArticleDAO articleDao;
	@Autowired
	private ArticleFileDAO articleFileDao;
	
	@Transactional
	@Override
	public void create(ArticleDto dto) throws Exception {
		
		String[] files = dto.getFiles();
		
		if(files == null) {
			articleDao.create(dto);
			articleDao.updateWriterImg(dto);
			return;
		}
		
		dto.setFileCnt(files.length);
		articleDao.create(dto);
		articleDao.updateWriterImg(dto);
		logger.info("Create - "+dto.toString());
		Integer article_no = dto.getArticle_no();
		
		for(String fileName : files) {
			articleFileDao.addAttach(fileName, article_no);
		}	
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ArticleDto read(Integer article_no) throws Exception {
		articleDao.updateViewCnt(article_no);
	    return articleDao.read(article_no);
	}
	
	@Transactional
	@Override
	public void update(ArticleDto dto) throws Exception {
		
		int article_no = dto.getArticle_no();
		articleFileDao.deleteAllAttach(article_no);
		
		String[] files = dto.getFiles();
		
		if(files == null) {
			articleDao.update(dto);
			dto.setFileCnt(0);
			return;
		}
		
		dto.setFileCnt(files.length);
		articleDao.update(dto);
		for(String fileName : files) {
			articleFileDao.replaceAttach(fileName, article_no);
		}
	}
	
	@Transactional
	@Override
	public void delete(Integer article_no) throws Exception {
		articleFileDao.deleteAllAttach(article_no);
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
	
	@Override
	public List<ArticleDto> listSearch(SearchCondition searchCondition) throws Exception {
	    return articleDao.listSearch(searchCondition);
	}

	@Override
	public int countSearchedArticles(SearchCondition searchCondition) throws Exception {
	    return articleDao.countSearchedArticles(searchCondition);
	}
	
	@Override
	public List<ArticleDto> userBoardList(String userId) throws Exception {
		return articleDao.userBoardList(userId);
	}
	

	

}
