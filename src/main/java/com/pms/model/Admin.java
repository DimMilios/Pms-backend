package com.pms.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(
            {
                    @JoinColumn(name = "user_profile_id", referencedColumnName = "id"),
                    @JoinColumn(name = "user_profile_role", referencedColumnName = "role")
            }
    )
    private UserProfile userProfile;

    @Nullable
    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
//            @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
//            @AttributeOverride(name = "fatherName", column = @Column(name = "father_name")),
//    })
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
