package com.oyster.myboard.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.oyster.myboard.domain.LoginDto;
import com.oyster.myboard.domain.UserDto;
import com.oyster.myboard.service.UserService;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
    // 로그인 페이지
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(@ModelAttribute("loginDto") LoginDto loginDto) {
        return "/user/login";
    }

    // 로그인 처리
    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public void loginPOST(LoginDto loginDto, HttpSession session, Model m) throws Exception {

        UserDto userDto = userService.login(loginDto);

        if (userDto == null || !BCrypt.checkpw(loginDto.getUserPw(), userDto.getUserPw())) {
            return;
        }

        m.addAttribute("user", userDto);
        
        if(loginDto.isUseCookie()) { //로그인 상태 유지를 선택할 경우
        	int amount = 60 * 60 * 24 * 7;  //Cookie의 유효시간을 7일
        	Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount)); //로그인 유지기간 설정
        	userService.keepLogin(userDto.getUserId(), session.getId(), sessionLimit);
        }

    }
    
    // 로그아웃 처리
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session) throws Exception {

        Object object = session.getAttribute("login");
        if (object != null) {
            UserDto userDto = (UserDto) object;
            session.removeAttribute("login"); //session 의 login값 제거
            session.invalidate();  // 세션 종료시킴
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
            if (loginCookie != null) {
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
                userService.keepLogin(userDto.getUserId(), "none", new Date());
            }
        }

        return "/user/logout";
    }

}
