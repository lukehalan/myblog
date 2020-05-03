package com.lukehalan.myblog.controller;


import javax.validation.Valid;

import com.lukehalan.myblog.entities.UserEntity;
import com.lukehalan.myblog.services.IUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;


@Controller
public class RegistrationController {

    private final IUserService iUserService;

    @Autowired
    public RegistrationController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String doRegistration(Model model) {
        model.addAttribute("user", new UserEntity());
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(@Valid UserEntity userEntity,
                             BindingResult result,
                             Model model) {

        if (iUserService.findByEmail(userEntity.getEmail()).isPresent()) {
            result
                    .rejectValue("email", "error.user",
                            "A user already registered with this email");
        }
        if (iUserService.findByUsername(userEntity.getUsername()).isPresent()) {
            result
                    .rejectValue("username", "error.user",
                            "A user already registered with this username");
        }

        if (!result.hasErrors()) {
            iUserService.save(userEntity);
            model.addAttribute("successMessage", "The User has registered successfully");
            model.addAttribute("user", new UserEntity());
        }

        return "/registration";
    }
}
