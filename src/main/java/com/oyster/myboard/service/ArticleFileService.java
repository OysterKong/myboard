package com.oyster.myboard.service;

import java.util.List;

public interface ArticleFileService {

    // 파일 목록 가져오기
    List<String> getAttach(Integer article_no) throws Exception;

    // 파일 삭제
    void deleteAttach(String file_name) throws Exception;
    
    // 첨부파일 수량 갱신
	void updateAttachCnt(Integer article_no) throws Exception;

}