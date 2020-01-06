package com.pms.model.staff;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.PhoneNumber;
import com.pms.model.userprofile.UserProfile;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique=true, nullable = false)
    protected Long id;

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
    @JsonUnwrapped
    private FullName fullName;

    @Nullable
    @Embedded
    @JsonUnwrapped
    private Address address;

    @ElementCollection
    @CollectionTable(
            name="phone_no",
            joinColumns = @JoinColumn(name = "staff_id")
    )
    @Column(name = "phone_no")
    private Set<PhoneNumber> phoneNumbers;

    public Staff() {

    }

    public Staff(UserProfile userProfile, Set<PhoneNumber> phoneNumbers, Address address) {
        this.userProfile = userProfile;
        this.phoneNumbers = phoneNumbers;
        this.address = address;
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

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setAddress(@Nullable Address address) {
        this.address = address;
    }

    @Nullable
    public Address getAddress() {
        return address;
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
                "id=" + id +
                ", userProfile=" + userProfile +
                ", phoneNumbers=" + phoneNumbers +
                ", fullName=" + fullName +
                ", address=" + address +
                '}';
    }
}
