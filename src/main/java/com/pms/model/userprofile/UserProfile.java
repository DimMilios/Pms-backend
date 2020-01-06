package com.pms.model.userprofile;

import com.pms.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

@Entity
@Table(name = "user_profiles")
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    // TODO: ADD ENCRYPTION
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

//    public UserProfile(@NotBlank String username, @NotBlank String password, @NotBlank String email, @NotBlank Role role) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.role = role;
//    }

    public UserProfile() {
    }

//    public UserProfile(long id, String username, String password, String email, Role role) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.role = role;
//    }


    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}
