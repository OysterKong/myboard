package com.oyster.myboard.domain;

import java.util.Arrays;
import java.util.Date;

public class ArticleDto {
	
	private Integer article_no;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;
	private int replyCnt;
	
	private String[] files;
	private int fileCnt;
	
	
	public Integer getArticle_no() {
		return article_no;
	}
	
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public int getViewCnt() {
		return viewCnt;
	}
	
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	public int getReplyCnt() {
		return replyCnt;
	}
	
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	
	public String[] getFiles() {
		return files;
	}
	
	public void setFiles(String[] files) {
		this.files = files;
	}
	
	public int getFileCnt() {
		return fileCnt;
	}
	
	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	
	@Override
	public String toString() {
		return "ArticleDto [article_no=" + article_no + ", title=" + title + ", content=" + content + ", writer="
				+ writer + ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", replyCnt=" + replyCnt + ", files="
				+ Arrays.toString(files) + ", fileCnt=" + fileCnt + "]";
	}




}
