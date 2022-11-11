package com.oyster.myboard.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.oyster.myboard.domain.UserDto;
import com.oyster.myboard.service.UserService;

public class RememberMeInterceptor extends HandlerInterceptorAdapter {
	
    private static final Logger logger = LoggerFactory.getLogger(RememberMeInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if (loginCookie != null) {
            UserDto userDto = userService.checkLoginBefore(loginCookie.getValue());
            if (userDto != null)
                httpSession.setAttribute("login", userDto);
        }

        return true;
    }

}
