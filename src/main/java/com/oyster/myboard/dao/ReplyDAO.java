package com.oyster.myboard.dao;

import java.util.List;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.domain.ReplyDto;

public interface ReplyDAO {

	List<ReplyDto> list(Integer article_no) throws Exception;

	void create(ReplyDto dto) throws Exception;

	void update(ReplyDto dto) throws Exception;

	void delete(Integer reply_no) throws Exception;
	
	List<ReplyDto> listPaging(Integer article_no, PageStandard standard) throws Exception;

	int countReplies(Integer article_no) throws Exception;

	int getArticleNo(Integer reply_no) throws Exception;
	
	//회원이 작성한 댓글 목록
	List<ReplyDto> userReplies(String userId) throws Exception;

}