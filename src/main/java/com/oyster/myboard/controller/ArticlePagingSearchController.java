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
import com.oyster.myboard.commons.paging.SearchCondition;
import com.oyster.myboard.domain.ArticleDto;
import com.oyster.myboard.service.ArticleService;

@Controller
@RequestMapping("/article/paging/search")
public class ArticlePagingSearchController {

	 private static final Logger logger = LoggerFactory.getLogger(ArticlePagingSearchController.class);
	 	
	 	@Autowired
		private ArticleService articleService;

//	    @RequestMapping(value = "/list", method = RequestMethod.GET)
//	    public String list(@ModelAttribute("searchCondition") SearchCondition searchCondition,
//	                       Model m) throws Exception {
//
//	        logger.info("search list() called ...");
//
//	        PageMaker pageMaker = new PageMaker();
//	        pageMaker.setPageStandard(searchCondition);
//	        pageMaker.setTotalCount(articleService.countArticles(searchCondition));
//
//	        m.addAttribute("articles", articleService.listPageStandard(searchCondition));
//	        m.addAttribute("pageMaker", pageMaker);
//
//	        return "article/search/list";
//	    }
	    
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public String listPaging(@ModelAttribute("searchCondition") SearchCondition searchCondition,
									Model m) throws Exception {
		    
			logger.info("search list() called ...");

		    PageMaker pageMaker = new PageMaker();
		    pageMaker.setPageStandard(searchCondition);
		    //pageMaker.setTotalCount(articleService.countArticles(standard));
		    pageMaker.setTotalCount(articleService.countSearchedArticles(searchCondition));

		    //m.addAttribute("articles", articleService.listPageStandard(standard));
		    m.addAttribute("articles", articleService.listSearch(searchCondition));
		    m.addAttribute("pageMaker", pageMaker);

		    return "/article/search/list";
		}
	    
		//등록페이지로 이동
		@RequestMapping(value="/write", method=RequestMethod.GET)
		public String writeGETSearch() {
			
			logger.info("search writeGET() called ...");
			
			return "/article/search/write";
		}
		
		//등록 처리
		@RequestMapping(value="/write", method=RequestMethod.POST)
		public String writePOSTPaging(ArticleDto dto, RedirectAttributes rattr) throws Exception {
			
			logger.info("search writePOST() called ...");

			articleService.create(dto);
			rattr.addFlashAttribute("msg", "regSuccess");
			
			return "redirect:/article/paging/search/list";
		}
		
		//페이징 처리시 페이지 읽어오기
		@RequestMapping(value = "/read", method = RequestMethod.GET)
		public String readPaging(@RequestParam("article_no") int article_no,
								@ModelAttribute("searchCondition") SearchCondition searchCondition,
		                         Model m) throws Exception {
			
			logger.info("search readGET() called ...");
		    m.addAttribute("article", articleService.read(article_no));

		    return "/article/search/read";
		}
		
		//수정 시 수정한 글 페이징 읽어오기
		@RequestMapping(value = "/modify", method = RequestMethod.GET)
		public String modifyGETPaging(@RequestParam("article_no") int article_no,
									@ModelAttribute("searchCondition") SearchCondition searchCondition,
		                              Model m) throws Exception {

		    logger.info("search modifyGET() called ...");
		    logger.info(searchCondition.toString());
		    m.addAttribute("article", articleService.read(article_no));

		    return "/article/search/modify";
		}
		
		//페이징 시 수정처리
		@RequestMapping(value = "/modify", method = RequestMethod.POST)
		public String modifyPOSTPaging(ArticleDto dto,
									   SearchCondition searchCondition,
		                               RedirectAttributes rattr) throws Exception {

		    logger.info("search modifyPOST() called ...");
		    articleService.update(dto);
		    rattr.addAttribute("page", searchCondition.getPage());
		    rattr.addAttribute("perPageNum", searchCondition.getPerPageNum());
		    rattr.addAttribute("searchType", searchCondition.getSearchType());
		    rattr.addAttribute("keyword", searchCondition.getKeyword());
		    rattr.addFlashAttribute("msg", "modSuccess");

		    return "redirect:/article/paging/search/list";
		}
		
		//페이징 시 삭제처리
		@RequestMapping(value = "/remove", method = RequestMethod.POST)
		public String removePaging(@RequestParam("article_no") int article_no,
								   SearchCondition searchCondition,
		                           RedirectAttributes rattr) throws Exception {

		    logger.info("search removePOST() called ...");
		    articleService.delete(article_no);
		    rattr.addAttribute("page", searchCondition.getPage());
		    rattr.addAttribute("perPageNum", searchCondition.getPerPageNum());
		    rattr.addAttribute("searchType", searchCondition.getSearchType());
		    rattr.addAttribute("keyword", searchCondition.getKeyword());
		    rattr.addFlashAttribute("msg", "delSuccess");

		    return "redirect:/article/paging/search/list";
		}
	
	
}
