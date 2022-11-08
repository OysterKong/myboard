package com.oyster.myboard.domain;

import java.util.Date;

public class ReplyDto {
	
	private Integer reply_no;
	private Integer article_no;
	private String reply_next;
	private String reply_writer;
	private Date reg_date;
	private Date update_date;
	
	public Integer getReply_no() {
		return reply_no;
	}
	
	public void setReply_no(Integer reply_no) {
		this.reply_no = reply_no;
	}
	
	public Integer getArticle_no() {
		return article_no;
	}
	
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	
	public String getReply_next() {
		return reply_next;
	}
	
	public void setReply_next(String reply_next) {
		this.reply_next = reply_next;
	}
	
	public String getReply_writer() {
		return reply_writer;
	}
	
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	
	public Date getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public Date getUpdate_date() {
		return update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	@Override
	public String toString() {
		return "ReplyDto [reply_no=" + reply_no + ", article_no=" + article_no + ", reply_next=" + reply_next
				+ ", reply_writer=" + reply_writer + ", reg_date=" + reg_date + ", update_date=" + update_date + "]";
	}
	

}