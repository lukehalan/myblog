package com.lukehalan.myblog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 6, message = "*Your username must have at least 6 characters")
    @NotEmpty(message = "*Please provide your name")
    private String username;

    @Column(name = "password", nullable = false)
    @Length(min = 6, message = "*Password must have at least 6 characters")
    @NotEmpty(message = "*Please enter your password")
    @JsonIgnore
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "*Please enter a valid Email address")
    @NotEmpty(message = "*Please enter an email")
    private String email;

    @Column(name = "name")
    @NotEmpty(message = "*Please enter your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please enter your last name")
    private String lastName;

    @Column(name = "active", nullable = false)
    private int active;

    @OneToMany(mappedBy = "user")
    private Collection<PostEntity> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<RoleEntity> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Collection<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Collection<PostEntity> posts) {
        this.posts = posts;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
}
