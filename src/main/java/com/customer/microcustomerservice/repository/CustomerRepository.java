package com.customer.microcustomerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.customer.microcustomerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    ArrayList<Customer> findAll();
    Customer findById(int id);
    Customer deleteById(int id);
    Customer save(Customer customer);
}
