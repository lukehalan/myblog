package com.lukehalan.myblog.controller;

import javax.validation.Valid;
import java.util.Optional;
import java.security.Principal;

import com.lukehalan.myblog.entities.CommentEntity;
import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.entities.UserEntity;
import com.lukehalan.myblog.services.ICommentService;
import com.lukehalan.myblog.services.IPostService;
import com.lukehalan.myblog.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class CommentController {

    private final IUserService iUserService;
    private final IPostService iPostService;
    private final ICommentService iCommentService;

    @Autowired
    public CommentController(IPostService iPostService, IUserService iUserService, ICommentService commentService) {
        this.iUserService = iUserService;
        this.iPostService = iPostService;
        this.iCommentService = commentService;
    }

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public String addNewPost(@Valid CommentEntity commentEntity,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "/commentForm";
        } else {
            iCommentService.save(commentEntity);
            return "redirect:/post/" + commentEntity.getPost().getId();
        }
    }

    @RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
    public String postCommentById(@PathVariable Long id,
                                  Principal principal,
                                  Model model) {

        Optional<PostEntity> optionalPostEntity = iPostService.findId(id);

        if (optionalPostEntity.isPresent()) {
            Optional<UserEntity> optionalUserEntity = iUserService.findByUsername(principal.getName());
            if (optionalUserEntity.isPresent()) {
                CommentEntity commentEntity = new CommentEntity();
                commentEntity.setUser(optionalUserEntity.get());
                commentEntity.setPost(optionalPostEntity.get());
                model.addAttribute("comment", commentEntity);
                return "/commentForm";
            } else {
                return "/error";
            }
        } else {
            return "/error";
        }
    }

}
