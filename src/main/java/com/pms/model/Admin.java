package com.pms.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns(
        {
            @JoinColumn(name = "user_profile_id", nullable = false, referencedColumnName = "id"),
            @JoinColumn(name = "role", nullable = false, referencedColumnName = "role")
        }
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserProfile userProfile;


}
