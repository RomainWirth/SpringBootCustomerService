package com.customer.microcustomerservice.web.controller;


import com.customer.microcustomerservice.dao.CustomerDao;
import com.customer.microcustomerservice.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

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

    // Ajout d'un client dans la BDD
    @ApiOperation("Méthode qui permet d'ajouter un client")
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        checkLicense(customer.getDrivingLicence());
        return customerDao.save(customer);
    }

    // Modification d'un client à l'id demandée
    @ApiOperation("Méthode pour modifier les infos d'un client")
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        checkLicense(customer.getDrivingLicence());
        Customer updateCustomer = customerDao.findById(id);

        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setBirthDate(customer.getBirthDate());
        updateCustomer.setDrivingLicence(customer.getDrivingLicence());

        return updateCustomer;
    }

    // suppression d'un client à l'id
    @ApiOperation("Méthode pour supprimer un client")
    @DeleteMapping("/customers/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        return customerDao.delete(id);
    }

    // méthode pour vérifier le numéro de permis de conduire
    public void checkLicense (String drivingLicence) {
        RestTemplate restTemplate = new RestTemplate();
        Boolean isValid = restTemplate.getForObject("http://localhost.8091/licenses" + drivingLicence, Boolean.class);
        if (Boolean.FALSE.equals(isValid)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Driving Licence Invalid"
            );
        }
    }
}
