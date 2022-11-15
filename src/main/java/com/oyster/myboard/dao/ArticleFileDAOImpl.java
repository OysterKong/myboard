package com.oyster.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleFileDAOImpl implements ArticleFileDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace="com.oyster.myboard.mappers.upload.ArticleFileMapper.";

	
    // 게시글 첨부파일 추가
	@Override
    public void addAttach(String file_name, Integer article_no) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("file_name", file_name);
        paramMap.put("article_no", article_no);
        session.insert(namespace + "addAttach", paramMap);
    }

    // 게시글 첨부파일 조회
	@Override
    public List<String> getAttach(Integer article_no) throws Exception {
        return session.selectList(namespace + "getAttach", article_no);
    }

    // 게시글 첨부파일 수정
	@Override
    public void replaceAttach(String file_name, Integer article_no) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("file_name", file_name);
        paramMap.put("article_no", article_no);
        session.insert(namespace + "replaceAttach", paramMap);
    }

    // 게시글 첨부파일 삭제
	@Override
    public void deleteAttach(String file_name) throws Exception {
    	session.delete(namespace + "deleteAttach", file_name);
    }

    // 게시글 첨부파일 일괄 삭제
	@Override
    public void deleteAllAttach(Integer article_no) throws Exception {
    	session.delete(namespace + "deleteAllAttach", article_no);
    }

    // 특정 게시글의 첨부파일 갯수 갱신
	@Override
    public void updateAttachCnt(Integer article_no) throws Exception {
    	session.update(namespace + "updateAttachCnt", article_no);
    }
	
	
	
	

}
