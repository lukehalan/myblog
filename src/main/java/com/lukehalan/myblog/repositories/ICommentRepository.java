package com.lukehalan.myblog.repositories;

import com.lukehalan.myblog.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICommentRepository extends JpaRepository<CommentEntity, Long> {
}
