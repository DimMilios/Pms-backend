package com.pms.model;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_profile_role", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_profile_id", insertable = false, updatable = false)
    private Long profileId;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumns(
            {
                    @JoinColumn(name = "user_profile_id", referencedColumnName = "id"),
                    @JoinColumn(name = "user_profile_role", referencedColumnName = "role")
            }
    )
    private UserProfile userProfile;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
            @AttributeOverride(name = "fatherName", column = @Column(name = "father_name")),
    })
    private FullName fullName;

    public Admin() {
//        this.role = Role.ADMIN;
    }

    public Admin(UserProfile userProfile, Long profileId) {
        this.userProfile = userProfile;
        this.profileId = profileId;
//        this.role = Role.ADMIN;
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

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", role=" + role +
                ", profileId=" + profileId +
                ", userProfile=" + userProfile +
                '}';
    }
}
