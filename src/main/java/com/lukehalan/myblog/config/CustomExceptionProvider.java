package com.lukehalan.myblog.config;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;



@ControllerAdvice
public class CustomExceptionProvider {

    private static Logger logger = LoggerFactory.getLogger(CustomExceptionProvider.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(final Throwable throwable) {
        logger.error("Execution of SpringSecurity error", throwable);
        ModelAndView mv = new ModelAndView("/error");
        String errMsg = (throwable != null ? throwable.toString() : "Unknown error");
        mv.addObject("errMsg", errMsg);
        return mv;
    }

}
