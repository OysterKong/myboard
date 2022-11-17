package com.oyster.myboard.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oyster.myboard.domain.UserDto;
import com.oyster.myboard.service.UserService;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 페이지로 이동
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "/user/register";
	}
	
	//회원가입 기능
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(UserDto dto, RedirectAttributes rattr,
											@RequestParam("userPw") String userPw,
											@RequestParam("userPw2") String userPw2) throws Exception {
		
		if(!userPw.equals(userPw2)) {
			rattr.addFlashAttribute("msg", "FAILURE");
			return "redirect:/user/register";
		}
		//BCrypt.hashpw( 첫번째파라미터 : 암호화할 비밀번호 , 두번째파라미터 : BCrypt.gensalt() ) = 암호화된 비밀번호 반환
        String hashedPw = BCrypt.hashpw(dto.getUserPw(), BCrypt.gensalt());
        dto.setUserPw(hashedPw);
        userService.register(dto); //BCrypt.gensalt()로 반환받은 암호화된 비밀번호를 회원객체(UserDto)에 저장한뒤 Service단의 회원가입 메서드 호출
        rattr.addFlashAttribute("msg", "REGISTERED");

        return "redirect:/user/login"; 
	}
	

	

}
