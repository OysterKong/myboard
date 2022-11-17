package com.oyster.myboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.commons.paging.SearchCondition;
import com.oyster.myboard.domain.ArticleDto;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	
	@Autowired
	private SqlSession session;
	private static String namespace="com.oyster.myboard.mappers.article.ArticleMapper.";
	
	@Override
	public void create(ArticleDto dto) throws Exception {
		session.insert(namespace + "create", dto);
	}
	
	@Override
	public ArticleDto read(Integer article_no) throws Exception {
		return session.selectOne(namespace + "read", article_no);
	}
	
	@Override
	public void update(ArticleDto dto) throws Exception {
		session.update(namespace + "update", dto);
	}
	
	@Override
	public void delete(Integer article_no) throws Exception {
		session.delete(namespace + "delete", article_no);
	}
	
	@Override
	public List<ArticleDto> listAll() throws Exception {
		return session.selectList(namespace + "listAll");
	}
	
	@Override
	public List<ArticleDto> listPaging(int page) throws Exception {
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page-1) * 10;
		
		return session.selectList(namespace + "listPaging", page);
	}
	
	@Override
	public List<ArticleDto> listPageStandard(PageStandard standard) throws Exception {
		return session.selectList(namespace + "listPageStandard", standard);
	}
	
	@Override
	public int countArticles(PageStandard standard) throws Exception {
		return session.selectOne(namespace + "countArticles", standard);
	}
	
	@Override
	public List<ArticleDto> listSearch(SearchCondition searchCondition) throws Exception {
		return session.selectList(namespace + "listSearch", searchCondition);
	}
	
	@Override
	public int countSearchedArticles(SearchCondition searchCondition) throws Exception {
		return session.selectOne(namespace + "countSearchedArticles", searchCondition);
	}
	
	@Override
	public void updateReplyCnt(Integer article_no, int amount) throws Exception {

	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("article_no", article_no);
	    paramMap.put("amount", amount);

	    session.update(namespace + "updateReplyCnt",paramMap);
	}
	
	@Override
	public void updateViewCnt(Integer article_no) throws Exception {
	    session.update(namespace + "updateViewCnt", article_no);
	}
	
	//회원이 작성한 게시글 목록
	@Override
	public List<ArticleDto> userBoardList(String userId) throws Exception {
		return session.selectList(namespace + "userBoardList", userId);
	}
	
	//회원 프로필 사진 수정
	@Override
	public void updateWriterImg(ArticleDto dto) throws Exception {
		session.update(namespace + "updateWriterImg", dto);
	}


}
