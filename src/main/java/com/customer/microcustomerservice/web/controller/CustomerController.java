package com.customer.microcustomerservice.web.controller;


import com.customer.microcustomerservice.dao.CustomerDao;
import com.customer.microcustomerservice.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api("API pour les opérations CRUD sur les clients.")
@RestController
public class CustomerController {
    private final CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    // à l'URL "customers", on montre la liste complète des clients
    @ApiOperation("Récupère la liste complète des clients")
    @GetMapping("/customers")
    public ArrayList<Customer> getCustomersList() {
        return customerDao.findAll();
    }
    // à l'URL customers/{id} (ou id est un des numéros d'id de client de la liste) on montre le client correspondant
    @ApiOperation("Récupère un client grâce à son ID à condition que celui-ci soit enregistré")
    @GetMapping("/customers/{id}")
    public Customer displayOneCustomer(@PathVariable int id) {
        return customerDao.findById(id);
    }

    @ApiOperation("Méthode qui permet d'ajouter un client")
    @PostMapping("/customers")
    public void addCustomer(@RequestBody Customer customer) {
        customerDao.save(customer);
    }

}
