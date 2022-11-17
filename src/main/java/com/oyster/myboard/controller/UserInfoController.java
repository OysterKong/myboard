package com.oyster.myboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oyster.myboard.commons.util.UploadFileUtils;
import com.oyster.myboard.domain.ArticleDto;
import com.oyster.myboard.domain.ReplyDto;
import com.oyster.myboard.domain.UserDto;
import com.oyster.myboard.service.ArticleService;
import com.oyster.myboard.service.ReplyService;
import com.oyster.myboard.service.UserService;

@Controller
@RequestMapping("/user")
public class UserInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ReplyService replyService;
	
	@Resource(name = "uimagePath")
	private String uimagePath;
	
	// 회원 프로필 이미지 수정
	@RequestMapping(value = "/modify/image", method = RequestMethod.POST)
	public String userImageModify(String userId, MultipartFile file, HttpSession session, RedirectAttributes rattr) throws Exception {
		if(file == null) {
			rattr.addFlashAttribute("msg", "FAIL");
			return "redirect:/user/profile";
		}
		String uploadFile = UploadFileUtils.uploadFile(uimagePath, file.getOriginalFilename(), file.getBytes());
		String front = uploadFile.substring(0, 12);
		String end = uploadFile.substring(14);
		String userImg = front + end;
		userService.modifyUserImage(userId, userImg);
		Object userObj = session.getAttribute("login");
		UserDto userDto = (UserDto) userObj;
		userDto.setUserImg(userImg);
		session.setAttribute("login", userDto);
		rattr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/profile";
	}
	
	// 회원정보 수정
	@RequestMapping(value = "/modify/info", method = RequestMethod.POST)
	public String userInfoModify(UserDto userDto, HttpSession session, RedirectAttributes rattr) throws Exception {
		logger.info(userDto.toString());
		UserDto oldUserInfo = userService.getUser(userDto.getUserId());
		logger.info(oldUserInfo.toString());
		logger.info("1");
		if(!BCrypt.checkpw(userDto.getUserPw(), oldUserInfo.getUserPw())) {
			rattr.addFlashAttribute("msg", "FAILURE");
			logger.info("2");
			return "redirect:/user/profile";
		}
		logger.info("3");
		userService.modifyUser(userDto);
		userDto.setUserJoinDate(oldUserInfo.getUserJoinDate());
		userDto.setUserLoginDate(oldUserInfo.getUserLoginDate());
		userDto.setUserImg(oldUserInfo.getUserImg());
		session.setAttribute("login", userDto);
		rattr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/profile";
	}
	
	//회원 비밀번호 수정
	@RequestMapping(value = "/modify/pw", method = RequestMethod.POST)
	public String userPwModify(@RequestParam("userId") String userId,
								@RequestParam("oldPw") String oldPw,
								@RequestParam("newPw") String newPw,
								@RequestParam("newPw2") String newPw2,
								HttpSession session, RedirectAttributes rattr) throws Exception {
		UserDto userInfo = userService.getUser(userId);
		if(!BCrypt.checkpw(oldPw, userInfo.getUserPw())) {
			rattr.addFlashAttribute("msg", "FAILURE");
			return "redirect:/user/profile";
		}
		if(BCrypt.checkpw(newPw, userInfo.getUserPw())) {
			rattr.addFlashAttribute("msg", "SAME");
			return "redirect:/user/profile";
		}
		if(!newPw.equals(newPw2)) {
			rattr.addFlashAttribute("msg", "FAILURE2");
			return "redirect:/user/profile";
		}
		String newHashPw = BCrypt.hashpw(newPw, BCrypt.gensalt());
		userInfo.setUserPw(newHashPw);
		userService.modifyPw(userInfo);
		session.setAttribute("login", userInfo);
		rattr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/profile";	
	}
	
	//회원 정보 페이지
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpSession session, Model m) throws Exception {
		
		Object userObj = session.getAttribute("login");
		UserDto userDto = (UserDto) userObj;
		String userId = userDto.getUserId();
		List<ArticleDto> userBoardList = articleService.userBoardList(userId);
		List<ReplyDto> userReplies = replyService.userReplies(userId);
		
		m.addAttribute("userBoardList", userBoardList);
		m.addAttribute("userReplies", userReplies);
		
		return "user/profile";
	}
	
	
}
