package com.lukehalan.myblog.controller;

import java.util.Optional;

import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.entities.UserEntity;
import com.lukehalan.myblog.services.IPostService;
import com.lukehalan.myblog.services.IUserService;
import com.lukehalan.myblog.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class BlogController {

    private final IPostService iPostService;
    private final IUserService iUserService;

    @Autowired
    public BlogController(IUserService userService, IPostService postService) {
        this.iUserService = userService;
        this.iPostService = postService;
    }

    @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
    public String blogMapper(@PathVariable String username, @RequestParam(defaultValue = "0") int page,
                             Model model) {

        Optional<UserEntity> optionalUserEntity = iUserService.findByUsername(username);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            Page<PostEntity> posts = iPostService.findByUserOrderedByDatePageable(userEntity, page);
            Paging pager = new Paging(posts);
            model.addAttribute("user", userEntity);
            model.addAttribute("pager", pager);
            return "/posts";
        } else {
            return "/error";
        }
    }
}
