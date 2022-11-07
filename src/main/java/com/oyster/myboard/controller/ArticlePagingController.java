package com.oyster.myboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oyster.myboard.commons.paging.PageMaker;
import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.domain.ArticleDto;
import com.oyster.myboard.service.ArticleService;

@Controller
@RequestMapping("/article/paging")
public class ArticlePagingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticlePagingController.class);
	
	@Autowired
	private ArticleService articleService;
	
	//등록페이지로 이동
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String writeGETPaging() {
		
		logger.info("paging writeGET() called ...");
		
		return "/article/paging/write";
	}
	
	//등록 처리
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePOSTPaging(ArticleDto dto, RedirectAttributes rattr) throws Exception {
		
		logger.info("paging writePOST() called ...");

		articleService.create(dto);
		rattr.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:/article/paging/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPaging(Model m, PageStandard standard) throws Exception {
	    logger.info("paging listGET() called ...");

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setPageStandard(standard);
	    pageMaker.setTotalCount(articleService.countArticles(standard));

	    m.addAttribute("articles", articleService.listPageStandard(standard));
	    m.addAttribute("pageMaker", pageMaker);

	    return "/article/paging/list";
	}
	
	//페이징 처리시 페이지 읽어오기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readPaging(@RequestParam("article_no") int article_no,
	                         @ModelAttribute("standard") PageStandard standard,
	                         Model m) throws Exception {
		
		logger.info("paging readGET() called ...");
	    m.addAttribute("article", articleService.read(article_no));

	    return "/article/paging/read";
	}
	
	//수정 시 수정한 글 페이징 읽어오기
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyGETPaging(@RequestParam("article_no") int article_no,
								  @ModelAttribute("standard") PageStandard standard,
	                              Model m) throws Exception {

	    logger.info("paging modifyGET() called ...");
	    m.addAttribute("article", articleService.read(article_no));

	    return "/article/paging/modify";
	}
	
	//페이징 시 수정처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOSTPaging(ArticleDto dto,
								   PageStandard standard,
	                               RedirectAttributes rattr) throws Exception {

	    logger.info("paging modifyPOST() called ...");
	    articleService.update(dto);
	    rattr.addAttribute("page", standard.getPage());
	    rattr.addAttribute("perPageNum", standard.getPerPageNum());
	    rattr.addFlashAttribute("msg", "modSuccess");

	    return "redirect:/article/paging/list";
	}
	
	//페이징 시 삭제처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePaging(@RequestParam("article_no") int article_no,
							   PageStandard standard,
	                           RedirectAttributes rattr) throws Exception {

	    logger.info("paging removePOST() called ...");
	    articleService.delete(article_no);
	    rattr.addAttribute("page", standard.getPage());
	    rattr.addAttribute("perPageNum", standard.getPerPageNum());
	    rattr.addFlashAttribute("msg", "delSuccess");

	    return "redirect:/article/paging/list";
	}


	

}
