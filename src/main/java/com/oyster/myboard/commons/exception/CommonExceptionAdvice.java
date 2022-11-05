package com.oyster.myboard.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice            //ExceptionHandler은 하나의 클래스만 대상, ControllerAdvice는 모든 컨트롤러 대상
public class CommonExceptionAdvice {
	
    private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

    @ExceptionHandler(Exception.class) //일반클래스와는 다르게 Model 타입을 파라미터로 사용하지 않아서 ModelAndView사용
    public ModelAndView commonException(Exception e) {

        logger.info(e.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("/commons/common_error");

        return modelAndView;
    }

}
