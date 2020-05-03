package com.lukehalan.myblog.repositories;

import com.lukehalan.myblog.entities.UserEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(@Param("username") String username);
    Optional<UserEntity> findByEmail(@Param("email") String email);
}

