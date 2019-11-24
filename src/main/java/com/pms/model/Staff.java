package com.pms.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

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

    @OneToMany(mappedBy = "staff")
    private List<PhoneNumber> phoneNumbers;

    private String city;

    private String address;

    private int streetNumber;

    private int zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
