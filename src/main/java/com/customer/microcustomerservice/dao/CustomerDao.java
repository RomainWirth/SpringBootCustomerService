package com.customer.microcustomerservice.dao;

import com.customer.microcustomerservice.model.Customer;

import java.util.ArrayList;

public interface CustomerDao {
    ArrayList<Customer> findAll();
    Customer findById(int id);
    Customer save(Customer customer);
    Customer delete(int id);
}
