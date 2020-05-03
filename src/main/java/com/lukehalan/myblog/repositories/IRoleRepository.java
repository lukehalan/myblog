package com.lukehalan.myblog.repositories;

import com.lukehalan.myblog.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(@Param("role") String role);

}
