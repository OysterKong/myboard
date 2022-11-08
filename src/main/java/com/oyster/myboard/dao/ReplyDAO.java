package com.oyster.myboard.dao;

import java.util.List;

import com.oyster.myboard.domain.ReplyDto;

public interface ReplyDAO {

	List<ReplyDto> list(Integer article_no) throws Exception;

	void create(ReplyDto dto) throws Exception;

	void update(ReplyDto dto) throws Exception;

	void delete(Integer reply_no) throws Exception;

}