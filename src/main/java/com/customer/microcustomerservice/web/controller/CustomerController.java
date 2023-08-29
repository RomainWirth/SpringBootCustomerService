package com.customer.microcustomerservice.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @GetMapping("/customers")
    public String customersList() {
        return "customer example";
    }
    @GetMapping("/customers/{id}")
    public String displayOneCustomer(@PathVariable int id) {
        return "You asked for customer n° : " + id;
    }
}
