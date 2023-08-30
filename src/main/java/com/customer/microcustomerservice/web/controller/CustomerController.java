package com.customer.microcustomerservice.web.controller;


import com.customer.microcustomerservice.dao.CustomerDao;
import com.customer.microcustomerservice.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CustomerController {
    // Création d'une liste de Customers
//    private ArrayList<Customer> customerList = new ArrayList<>(List.of(
//            new Customer(1, "D'Artagnan", "Charles", new Date(), "13AA00001"),
//            new Customer(2, "de Sillègue d'Athos d'Autevielle", "Armand", new Date(), "14AA00001"),
//            new Customer(3, "de Portos", "Isaac", new Date(), "15AA00001"),
//            new Customer(4, "d'Aramis", "Henri", new Date(), "16AA00001")
//    ));
    private final CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    // à l'URL "customers", on montre la liste complète des clients
    @GetMapping("/customers")
    public ArrayList<Customer> getCustomersList() {
//        return customerList;
        return customerDao.findAll();
    }
    // à l'URL customers/{id} (ou id est un des numéros d'id de client de la liste) on montre le client correspondant
    @GetMapping("/customers/{id}")
    public Customer displayOneCustomer(@PathVariable int id) {
//        for (Customer customer : customerList) { // boucle for sur le arraylist customerList
//            if (id == customer.getId()) { // comparaison de l'id passée dans l'URL avec l'id de chaque client de customerList
//                return customer; // renvoie le customer correspondant
//            }
//        }
//        return null; // sinon ne renvoie rien
        return customerDao.findById(id);
    }
}
