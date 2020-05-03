package com.lukehalan.myblog.services;

import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.entities.UserEntity;
import org.springframework.data.domain.Page;



import java.util.Optional;

public interface IPostService {

    PostEntity save(PostEntity post);
    void delete(PostEntity post);
    Optional<PostEntity> findId(Long id);

    Page<PostEntity> findAllOrderedByDatePageable(int page);

    Page<PostEntity> findByUserOrderedByDatePageable(UserEntity user, int page);


}
