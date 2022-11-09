package com.oyster.myboard.service;

import java.util.List;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.domain.ReplyDto;

public interface ReplyService {

	List<ReplyDto> list(Integer article_no) throws Exception;

	void create(ReplyDto dto) throws Exception;

	void update(ReplyDto dto) throws Exception;

	void delete(Integer reply_no) throws Exception;
	
	List<ReplyDto> getRepliesPaging(Integer article_no, PageStandard standard) throws Exception;

	int countReplies(Integer article_no) throws Exception;

}