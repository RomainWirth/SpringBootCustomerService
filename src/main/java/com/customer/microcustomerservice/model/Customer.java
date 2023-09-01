package com.customer.microcustomerservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="customer_lastname")
    private String lastName;

    @Basic
    @Column(name="customer_firstname")
    private String firstName;

    @Basic
    @Column(name="customer_birthdate")
    private Date birthDate;

    @Basic
    @Column(name="customer_drivinglicence")
    private String drivingLicence;

    public Customer() {
    }

    public Customer(int id, String lastName, String firstName, Date birthDate, String drivingLicence) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.drivingLicence = drivingLicence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", drivingLicence='" + drivingLicence + '\'' +
                '}';
    }
}
