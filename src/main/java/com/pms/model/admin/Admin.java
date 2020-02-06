package com.pms.model.admin;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * The type Admin.
 */
@Entity(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile;

    @Nullable
    @Embedded
    @JsonUnwrapped
    private FullName fullName;

    /**
     * Instantiates a new Admin.
     */
    public Admin() {
    }

    /**
     * Instantiates a new Admin.
     *
     * @param userProfile the user profile
     * @param fullName    the full name
     */
    public Admin(UserProfile userProfile,  FullName fullName) {
        this.userProfile = userProfile;
        this.fullName = fullName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets user profile.
     *
     * @return the user profile
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Sets user profile.
     *
     * @param userProfile the user profile
     */
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public FullName getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
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
