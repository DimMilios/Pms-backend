package com.pms.model.staff;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "payment_staff")
@DiscriminatorValue("Payment_staff")
public class PaymentStaff extends Staff {
}
