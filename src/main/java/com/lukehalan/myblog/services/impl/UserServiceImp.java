package com.lukehalan.myblog.services.impl;

import com.lukehalan.myblog.entities.UserEntity;
import com.lukehalan.myblog.repositories.IRoleRepository;
import com.lukehalan.myblog.repositories.IUserRepository;
import com.lukehalan.myblog.services.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository iRoleRepository;
    private final IUserRepository iUserRepository;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImp(IUserRepository iUserRepository, IRoleRepository iRoleRepository, PasswordEncoder passwordEncoder) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        user.setActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(iRoleRepository.findByRole(USER_ROLE)));
        return iUserRepository.saveAndFlush(user);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }



}
