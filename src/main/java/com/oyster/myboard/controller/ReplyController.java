package com.oyster.myboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oyster.myboard.commons.paging.PageMaker;
import com.oyster.myboard.commons.paging.PageStandard;
import com.oyster.myboard.domain.ReplyDto;
import com.oyster.myboard.service.ReplyService;

@RestController    //@Controller + @ResponseBody 합친 것
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글 등록
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyDto dto) {
		
		ResponseEntity<String> entity = null;
		try {
			replyService.create(dto);
			entity = new ResponseEntity<String>("regSuccess", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 목록
    @RequestMapping(value = "/all/{article_no}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyDto>> list(@PathVariable("article_no") Integer article_no) {
        
    	ResponseEntity<List<ReplyDto>> entity = null;
        try {
            entity = new ResponseEntity<List<ReplyDto>>(replyService.list(article_no), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<List<ReplyDto>>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
	
    //댓글 페이징 목록
    @RequestMapping(value = "/{article_no}/{page}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listPaging(@PathVariable("article_no") Integer article_no,
    											@PathVariable("page") Integer page) {
    	
    	ResponseEntity<Map<String, Object>> entity = null;
    	
    	try {
    		PageStandard standard = new PageStandard();
    		standard.setPage(page);
    		
    		List<ReplyDto> replies = replyService.getRepliesPaging(article_no, standard);
    		int repliesCount = replyService.countReplies(article_no);
    		
    		PageMaker pageMaker = new PageMaker();
    		pageMaker.setStandard(standard);
    		pageMaker.setTotalCount(repliesCount);
    		
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("replies", replies);
    		map.put("pageMaker", pageMaker);
    		
    		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
    	}
    	return entity;
    }
	
    //댓글 수정
    @RequestMapping(value = "/{reply_no}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("reply_no") Integer reply_no, @RequestBody ReplyDto dto) {
        ResponseEntity<String> entity = null;
        
        try {
            dto.setReply_no(reply_no);
            replyService.update(dto);
            entity = new ResponseEntity<String>("modSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    
    //댓글 삭제
    @RequestMapping(value = "/{reply_no}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("reply_no") Integer reply_no) {
        
    	ResponseEntity<String> entity = null;
        try {
            replyService.delete(reply_no);
            entity = new ResponseEntity<String>("delSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
	
	
	
}
