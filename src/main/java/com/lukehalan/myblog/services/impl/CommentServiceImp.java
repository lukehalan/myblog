package com.lukehalan.myblog.services.impl;


import com.lukehalan.myblog.entities.CommentEntity;
import com.lukehalan.myblog.repositories.ICommentRepository;
import com.lukehalan.myblog.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements ICommentService {

    private final ICommentRepository iCommentRepository;

    @Autowired
    public CommentServiceImp(ICommentRepository iCommentRepository) {
        this.iCommentRepository = iCommentRepository;
    }


    @Override
    public CommentEntity save(CommentEntity comment) {
        return iCommentRepository.saveAndFlush(comment);
    }
}
