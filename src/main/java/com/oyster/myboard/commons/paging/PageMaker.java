package com.oyster.myboard.commons.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private int displayPageNum = 10; // 하단 페이지 번호의 갯수

    private PageStandard standard;
    
    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", standard.getPerPageNum())
                .build();

        return uriComponents.toUriString();
    }

    public void setPageStandard(PageStandard standard) {
        this.standard = standard;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }
    
	// 게시글의 전체 갯수가 결정되면 calcData 메소드를 호출하여 계산 실행
    private void calcData() { 

        endPage = (int) (Math.ceil(standard.getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) standard.getPerPageNum()));

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;

        next = endPage * standard.getPerPageNum() >= totalCount ? false : true;

    }

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public PageStandard getStandard() {
		return standard;
	}

	public void setStandard(PageStandard standard) {
		this.standard = standard;
	}

	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", standard=" + standard + "]";
	}
    
    

}
