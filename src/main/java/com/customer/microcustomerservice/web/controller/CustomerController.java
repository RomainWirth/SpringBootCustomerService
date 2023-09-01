package com.customer.microcustomerservice.web.controller;

import com.customer.microcustomerservice.model.Customer;
import com.customer.microcustomerservice.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RequestMapping("/customers")

@Api("API pour les opérations CRUD sur les clients.")
@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation("Méthode pour ajouter un nouveau Customer")
    @PostMapping
    public @ResponseBody Customer addNewCustomer (@RequestBody Customer newCustomer) {
        checkLicense(newCustomer.getLicenseId());
        return customerRepository.save(newCustomer);
    }

    @ApiOperation("Méthode pour afficher tous les Customers")
    @GetMapping
    public @ResponseBody ArrayList<Customer> getCustomersList() {
        return customerRepository.findAll();
    }

    @ApiOperation(("Méthode pour afficher un seul Customer selon son id"))
    @GetMapping("/{id}")
    public @ResponseBody Customer displayOneCustomer(@PathVariable int id) {
        return customerRepository.findById(id);
    }

    // Modification d'un client à l'id demandée
    @ApiOperation("Méthode pour modifier les infos d'un client")
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        checkLicense(customer.getLicenseId());
        return customerRepository.save(customer);
    }

    // suppression d'un client à l'id
    @ApiOperation("Méthode pour supprimer un client")
    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        return customerRepository.deleteById(id);
    }

    // méthode pour vérifier le numéro de permis de conduire
    public void checkLicense (String drivingLicence) {
        RestTemplate restTemplate = new RestTemplate();
        Boolean isValid = restTemplate.getForObject("http://localhost:8091/licenses/" + drivingLicence, Boolean.class);
        if (Boolean.FALSE.equals(isValid)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Driving Licence Invalid"
            );
        }
    }
}
