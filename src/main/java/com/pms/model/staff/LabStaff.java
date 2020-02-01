package com.pms.model.staff;

import com.pms.model.FullName;
import com.pms.model.userprofile.UserProfile;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lab_staff")
@DiscriminatorValue("Lab_staff")
public class LabStaff extends Staff{

}
