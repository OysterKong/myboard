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
@RequestMapping("/article")
public class ArticleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	
	//페이징 처리시 페이지 읽어오기
	@RequestMapping(value = "/readPaging", method = RequestMethod.GET)
	public String readPaging(@RequestParam("article_no") int article_no,
	                         @ModelAttribute("standard") PageStandard standard,
	                         Model m) throws Exception {

	    m.addAttribute("article", articleService.read(article_no));

	    return "/article/read_paging";
	}
	
	//수정 시 수정한 글 페이징 읽어오기
	@RequestMapping(value = "/modifyPaging", method = RequestMethod.GET)
	public String modifyGETPaging(@RequestParam("article_no") int article_no,
								  @ModelAttribute("standard") PageStandard standard,
	                              Model m) throws Exception {

	    logger.info("modifyGetPaging ...");
	    m.addAttribute("article", articleService.read(article_no));

	    return "/article/modify_paging";
	}
	
	//페이징 시 수정처리
	@RequestMapping(value = "/modifyPaging", method = RequestMethod.POST)
	public String modifyPOSTPaging(ArticleDto dto,
								   PageStandard standard,
	                               RedirectAttributes rattr) throws Exception {

	    logger.info("modifyPOSTPaging ...");
	    articleService.update(dto);
	    rattr.addAttribute("page", standard.getPage());
	    rattr.addAttribute("perPageNum", standard.getPerPageNum());
	    rattr.addFlashAttribute("msg", "modSuccess");

	    return "redirect:/article/listPaging";
	}
	
	//페이징 시 삭제처리
	@RequestMapping(value = "/removePaging", method = RequestMethod.POST)
	public String removePaging(@RequestParam("article_no") int article_no,
							   PageStandard standard,
	                           RedirectAttributes rattr) throws Exception {

	    logger.info("remove ...");
	    articleService.delete(article_no);
	    rattr.addAttribute("page", standard.getPage());
	    rattr.addAttribute("perPageNum", standard.getPerPageNum());
	    rattr.addFlashAttribute("msg", "delSuccess");

	    return "redirect:/article/listPaging";
	}
	
	
	@RequestMapping(value = "/listPaging", method = RequestMethod.GET)
	public String listPaging(Model m, PageStandard standard) throws Exception {
	    logger.info("listPaging ...");

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setPageStandard(standard);
	    pageMaker.setTotalCount(articleService.countArticles(standard));

	    m.addAttribute("articles", articleService.listPageStandard(standard));
	    m.addAttribute("pageMaker", pageMaker);

	    return "/article/list_paging";
	}
	
	
	//페이징 리스트
	@RequestMapping(value="/listPageStandard", method=RequestMethod.GET)
	public String listPagingStandard(Model m, PageStandard standard) throws Exception {
		
		logger.info("listPagingStandard ...");
		m.addAttribute("articles", articleService.listPageStandard(standard));
		
		return "/article/list_pagingstandard";
	}
	
	
	//등록페이지로 이동
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String writeGET() {
		
		logger.info("write GET...");
		
		return "/article/write";
	}
	
	//등록 처리
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String writePOST(ArticleDto dto, RedirectAttributes rattr) throws Exception {
		
		logger.info("write POST...");
		logger.info(dto.toString());
		articleService.create(dto);
		
		rattr.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:/article/list";
	}
	
	
	//목록 페이지로 이동
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model m) throws Exception {
		
		logger.info("list ...");
		m.addAttribute("articles", articleService.listAll());
		
		return "/article/list";
	}
	
	//조회 페이지로 이동
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public String read(@RequestParam("article_no") Integer article_no, Model m) throws Exception {
		
		logger.info("read ...");
		m.addAttribute("article", articleService.read(article_no));
		
		return "/article/read";
	}
	
	//수정 페이지로 이동
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modifyGET(@RequestParam("article_no") Integer article_no, Model m) throws Exception {
		
		logger.info("modifyGet ...");
		m.addAttribute("article", articleService.read(article_no));
		
		return "/article/modify";
	}
	
	//수정 처리
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(ArticleDto dto, RedirectAttributes rattr) throws Exception {
		
		logger.info("modifyPOST ...");
		articleService.update(dto);
		rattr.addFlashAttribute("msg", "modSuccess");
		
		return "redirect:/article/list";
	}
	
	//삭제 처리
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("article_no") Integer article_no, RedirectAttributes rattr) throws Exception {
		
		logger.info("remove ...");
		articleService.delete(article_no);
		rattr.addFlashAttribute("msg", "delSuccess");
		
		return "redirect:/article/list";
	}
	
	

}
