package com.oyster.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.dao.ArticleDAO;
import com.oyster.myboard.dao.ReplyDAO;
import com.oyster.myboard.domain.ReplyDto;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;
	@Autowired
	private ArticleDAO articleDao;
	
	
	@Override
	public List<ReplyDto> list(Integer article_no) throws Exception {
		return replyDao.list(article_no);
	}
	
//	@Override
//	public void create(ReplyDto dto) throws Exception {
//		replyDao.create(dto);
//	}
	
	@Override
	public void update(ReplyDto dto) throws Exception {
		replyDao.update(dto);
	}
	
//	@Override
//	public void delete(Integer reply_no) throws Exception {
//		replyDao.delete(reply_no);
//	}
	
	@Override
	public List<ReplyDto> getRepliesPaging(Integer article_no, PageStandard standard) throws Exception {
	    return replyDao.listPaging(article_no, standard);
	}

	@Override
	public int countReplies(Integer article_no) throws Exception {
	    return replyDao.countReplies(article_no);
	}
	
	@Transactional  // 트랜잭션 처리
	@Override
	public void addReply(ReplyDto dto) throws Exception {
		replyDao.create(dto); //댓글 등록
		articleDao.updateReplyCnt(dto.getArticle_no(), 1);  //댓글 갯수 증가
	}
	
	@Transactional  // 트랜잭션 처리
	@Override
	public void removeReply(Integer reply_no) throws Exception {
		int article_no = replyDao.getArticleNo(reply_no);  // 댓글의 게시물 번호 조회
		replyDao.delete(reply_no);  // 댓글 삭제
		articleDao.updateReplyCnt(article_no, -1);  // 댓글 갯수 감소
	}

}
