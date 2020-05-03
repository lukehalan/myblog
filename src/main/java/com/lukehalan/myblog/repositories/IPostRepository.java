package com.lukehalan.myblog.repositories;


import com.lukehalan.myblog.entities.PostEntity;
import com.lukehalan.myblog.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IPostRepository extends JpaRepository<PostEntity, Long> {

    Optional<PostEntity> findById(Long id);
    Page<PostEntity> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<PostEntity> findByUserOrderByCreateDateDesc(UserEntity user, Pageable pageable);

}
