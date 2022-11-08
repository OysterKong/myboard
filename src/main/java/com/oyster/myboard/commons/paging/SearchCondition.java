package com.oyster.myboard.commons.paging;

public class SearchCondition extends PageStandard {
	
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCondition [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	

}
