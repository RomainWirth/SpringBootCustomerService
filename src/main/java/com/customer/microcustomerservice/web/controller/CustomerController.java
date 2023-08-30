package com.customer.microcustomerservice.web.controller;


import com.customer.microcustomerservice.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// arraylist avec 3 cutsomers random
@RestController
public class CustomerController {

    private ArrayList<Customer> customerList = new ArrayList<>(List.of(
            new Customer(1, "D'Artagnan", "Charles", new Date(), "13AA00001"),
            new Customer(2, "de Sillègue d'Athos d'Autevielle", "Armand", new Date(), "14AA00001"),
            new Customer(3, "de Portos", "Isaac", new Date(), "15AA00001"),
            new Customer(4, "d'Aramis", "Henri", new Date(), "16AA00001")
    ));

    @GetMapping("/customers")
    public ArrayList<Customer> getCustomersList() {
        return customerList;
    }

    @GetMapping("/customers/{id}")
    public Customer displayOneCustomer(@PathVariable int id) {
        for (Customer customer : customerList) {
            if (id == customer.getId()) {
                return customer;
            }
        }
        return null;
    }
}
