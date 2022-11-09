package com.oyster.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.dao.ReplyDAO;
import com.oyster.myboard.domain.ReplyDto;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;
	
	
	@Override
	public List<ReplyDto> list(Integer article_no) throws Exception {
		return replyDao.list(article_no);
	}
	
	@Override
	public void create(ReplyDto dto) throws Exception {
		replyDao.create(dto);
	}
	
	@Override
	public void update(ReplyDto dto) throws Exception {
		replyDao.update(dto);
	}
	
	@Override
	public void delete(Integer reply_no) throws Exception {
		replyDao.delete(reply_no);
	}
	
	@Override
	public List<ReplyDto> getRepliesPaging(Integer article_no, PageStandard standard) throws Exception {
	    return replyDao.listPaging(article_no, standard);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
	    return replyDao.countReplies(article_no);
	}
	

}
