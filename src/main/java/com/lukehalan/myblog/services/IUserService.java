package com.lukehalan.myblog.services;

import com.lukehalan.myblog.entities.UserEntity;

import java.util.Optional;

public interface IUserService {

    UserEntity save(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
