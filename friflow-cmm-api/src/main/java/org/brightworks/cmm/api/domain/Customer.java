package org.brightworks.cmm.api.domain;

import org.aspectj.lang.annotation.control.CodeGenerationHint;
import org.brightworks.cmm.api.listener.CustomerNumberGeneratorListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Created by kyel on 9/11/2016.
 */
@Entity
@EntityListeners(CustomerNumberGeneratorListener.class)
public class Customer extends JpaModel{

    @Embedded
    private Name name;

    @Column(name = "CUSTOMER_NUMBER")
    private String customerNumber;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
