package com.pms.model.staff;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "receptionists")
@DiscriminatorValue("Receptionist")
public class Receptionist extends Staff{
}
