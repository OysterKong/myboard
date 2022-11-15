package com.oyster.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.myboard.dao.ArticleFileDAO;

@Service
public class ArticleFileServiceImpl implements ArticleFileService {
	
	@Autowired
	private ArticleFileDAO articleFileDao;


	@Override
	public List<String> getAttach(Integer article_no) throws Exception {
		return articleFileDao.getAttach(article_no);
	}
	
	@Transactional
	@Override
	public void deleteAttach(String file_name) throws Exception {
		articleFileDao.deleteAttach(file_name);
	}
	
	@Transactional
	@Override
	public void updateAttachCnt(Integer article_no) throws Exception {
		articleFileDao.updateAttachCnt(article_no);
	}

	
}
