package com.oyster.myboard.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
    private static final String LOGIN = "login";
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object userDto =  modelMap.get("user");

        if (userDto != null) {  //Model에 담겨져 온 회원정보가 null이 아닐시
            logger.info("new login success");  //login이 성공했다는 메시지를 띄우고
            session.setAttribute(LOGIN, userDto);  //회원정보를 LOGIN 이라는 키에 담는다
            
            if(request.getParameter("useCookie") != null) { //useCookie 가 null이 아닐시
            	logger.info("Cookie is valid");  // Cookie가 유효하다고 메시지를 띄우고
            	
            	Cookie loginCookie = new Cookie("loginCookie", session.getId()); //세션에 담긴 Id값을 loginCookie라는 키에 담아 객체를 만든다.
            	loginCookie.setPath("/myboard"); //loginCookie의 경로를 홈으로 세팅하고
            	loginCookie.setMaxAge(60*60*24*7); //쿠기의 유효시간을 7일로 설정
            	
            	response.addCookie(loginCookie); //쿠키를 전송
            }

            Object destination = session.getAttribute("destination");  //회원이 바라보던 페이지의 정보를 세션에서 가져와서 destination 에 담아
            response.sendRedirect(destination != null ? (String) destination : "/myboard");  // 3항연산자로 destination 이 null이 아니면 그쪽으로 이동, null이면 home으로 이동
        }

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        // 기존의 로그인 정보 제거
        if (session.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");
            session.removeAttribute(LOGIN);
        }

        return true;
    }

}
