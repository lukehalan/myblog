package com.lukehalan.myblog.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<UserEntity> users;

    @Column(name = "role", unique = true)
    private String role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
