package com.lukehalan.myblog.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BlogErrorController implements ErrorController {

    private static final String PATHSTRING = "/error";

    @GetMapping("/403")
    public ModelAndView error403() {
        return new ModelAndView("/403");
    }

    @RequestMapping(PATHSTRING)
    public ModelAndView error() {
        return new ModelAndView("/error");
    }

    @Override
    public String getErrorPath() {
        return PATHSTRING;
    }
}
