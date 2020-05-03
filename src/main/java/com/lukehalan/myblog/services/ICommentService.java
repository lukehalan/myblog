package com.lukehalan.myblog.services;


import com.lukehalan.myblog.entities.CommentEntity;

public interface ICommentService {
    CommentEntity save(CommentEntity comment);
}

