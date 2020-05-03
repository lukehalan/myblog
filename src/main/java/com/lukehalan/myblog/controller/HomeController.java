package com.lukehalan.myblog.controller;

import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.services.IPostService;
import com.lukehalan.myblog.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final IPostService iPostService;

    @Autowired
    public HomeController(IPostService iPostService) {
        this.iPostService = iPostService;
    }

    @GetMapping("/home")
    public String doHome(@RequestParam(defaultValue = "0") int page,
                         Model model) {
        Page<PostEntity> postList = iPostService.findAllOrderedByDatePageable(page);
        Paging pager = new Paging(postList);
        model.addAttribute("pager", pager);
        return "/home";
    }
}
