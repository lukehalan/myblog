package com.lukehalan.myblog.controller;

import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.entities.UserEntity;
import com.lukehalan.myblog.services.IPostService;
import com.lukehalan.myblog.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    private final IPostService iPostService;
    private final IUserService iUserService;

    @Autowired
    public PostController(IPostService iPostService, IUserService iUserService) {
        this.iPostService = iPostService;
        this.iUserService = iUserService;
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.GET)
    public String showNewPost(Principal principal,
                              Model model) {

        Optional<UserEntity> optionalUserEntity = iUserService.findByUsername(principal.getName());

        if (optionalUserEntity.isPresent()) {
            PostEntity postEntity = new PostEntity();
            postEntity.setUser(optionalUserEntity.get());
            model.addAttribute("post", postEntity);
            return "/postForm";
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/newPost", method = RequestMethod.POST)
    public String createNewPost(@Valid PostEntity postEntity,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "/postForm";
        } else {
            iPostService.save(postEntity);
            return "redirect:/blog/" + postEntity.getUser().getUsername();
        }
    }

    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPostById(@PathVariable Long id,
                               Principal principal,
                               Model model) {

        Optional<PostEntity> optionalPostEntity = iPostService.findId(id);
        if (optionalPostEntity.isPresent()) {
            PostEntity postEntity = optionalPostEntity.get();
            if (isPostOwner(principal, postEntity)) {
                model.addAttribute("post", postEntity);
                return "/postForm";
            } else {
                return "/403";
            }
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostById(@PathVariable Long id,
                              Principal principal,
                              Model model) {

        Optional<PostEntity> optionalPostEntity = iPostService.findId(id);

        if (optionalPostEntity.isPresent()) {
            PostEntity postEntity = optionalPostEntity.get();
            model.addAttribute("post", postEntity);
            if (isPostOwner(principal, postEntity)) {
                model.addAttribute("username", principal.getName());
            }
            return "/post";
        } else {
            return "/error";
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public String deletePostById(@PathVariable Long id,
                                 Principal principal) {

        Optional<PostEntity> optionalPostEntity = iPostService.findId(id);
        if (optionalPostEntity.isPresent()) {
            PostEntity postEntity = optionalPostEntity.get();
            if (isPostOwner(principal, postEntity)) {
                iPostService.delete(postEntity);
                return "redirect:/home";
            } else {
                return "/403";
            }
        } else {
            return "/error";
        }
    }

    private boolean isPostOwner(Principal principal, PostEntity post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }
}
