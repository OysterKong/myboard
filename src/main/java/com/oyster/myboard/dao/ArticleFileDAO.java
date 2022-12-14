package com.oyster.myboard.dao;

import java.util.List;

public interface ArticleFileDAO {
	
	//게시글 첨부파일 추가
	void addAttach(String file_name, Integer article_no) throws Exception;
	
	//게시글 첨부파일 조회
	List<String> getAttach(Integer article_no) throws Exception;
	
	//게시글 첨부파일 수정
	void replaceAttach(String file_name, Integer article_no) throws Exception;
	
	//게시글 첨부파일 삭제
	void deleteAttach(String file_name) throws Exception;
	
	//게시글 첨부파일 일괄 삭제
	void deleteAllAttach(Integer article_no) throws Exception;
	
	//특정 게시글의 첨부파일 갯수 갱신
	void updateAttachCnt(Integer article_no) throws Exception;
	


}