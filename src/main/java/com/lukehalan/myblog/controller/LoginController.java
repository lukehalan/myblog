package com.lukehalan.myblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String doLogin(Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "/login";
    }

}
