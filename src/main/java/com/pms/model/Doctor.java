package com.pms.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Staff {


}
