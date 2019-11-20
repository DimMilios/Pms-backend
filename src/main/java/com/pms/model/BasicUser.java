package com.pms.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class BasicUser implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    // TODO: ADD ENCRYPTION
    private String password;

    private String email;

    public Long getId() {
        return id;
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
}
