package com.pms.model.staff;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pms.model.Address;
import com.pms.model.FullName;
import com.pms.model.PhoneNumber;
import com.pms.model.userprofile.UserProfile;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type", discriminatorType = DiscriminatorType.STRING)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique=true, nullable = false)
    protected Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id", unique = true)
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

    @Transient
    public String getStaffType(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );

        return val == null ? null : val.value();
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
                ", staffType=" + getStaffType() +
                '}';
    }


}
