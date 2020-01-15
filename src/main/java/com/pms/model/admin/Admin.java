package com.pms.model.admin;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumns(
            {
                    @JoinColumn(name = "user_profile_id", referencedColumnName = "id"),
                    @JoinColumn(name = "user_profile_role", referencedColumnName = "role")
            }
    )
    private UserProfile userProfile;

    @Nullable
    @Embedded
    @JsonUnwrapped
    private FullName fullName;

    public Admin() {
    }

    public Admin(UserProfile userProfile,  FullName fullName) {
        this.userProfile = userProfile;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userProfile=" + userProfile +
                ", fullName=" + fullName +
                '}';
    }
}
