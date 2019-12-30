package com.pms.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "staff")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(
            {
                    @JoinColumn(name = "user_profile_id", referencedColumnName = "id"),
                    @JoinColumn(name = "user_profile_role", referencedColumnName = "role")
            }
    )
    private UserProfile userProfile;

    @ElementCollection
    @CollectionTable(
            name="phone_no",
            joinColumns = @JoinColumn(name = "staff_id")
    )
    @Column(name = "phone_no")
    private Set<PhoneNumber> phoneNumbers;

    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
//            @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
//            @AttributeOverride(name = "fatherName", column = @Column(name = "father_name")),
//    })
    private FullName fullName;

    private String city;

    private String address;

    private int streetNumber;

    private int zipCode;

    public Staff() {

    }

    public Staff(UserProfile userProfile, Set<PhoneNumber> phoneNumbers, String city, String address, int streetNumber, int zipCode) {
        this.userProfile = userProfile;
        this.phoneNumbers = phoneNumbers;
        this.city = city;
        this.address = address;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + id +
                ", userProfile=" + userProfile +
                ", fullName=" + fullName +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
