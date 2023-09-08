package com.customer.microcustomerservice.web.controller;

import com.customer.microcustomerservice.model.Customer;
import com.customer.microcustomerservice.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/customers")

@Api("API pour les opérations CRUD sur les clients.")
@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation("Méthode pour ajouter un nouveau Customer")
    @PostMapping
    public @ResponseBody Customer addNewCustomer (@RequestBody Customer newCustomer) {
        if (isCustomerExistent(newCustomer.getLicenceNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Client Existant"
            );
        } else {
            isAgeValid(newCustomer.getBirthDate()); // vérificaton de l'âge
            if (isAgeValid(newCustomer.getBirthDate())) {
                checkLicense(newCustomer.getLicenceNumber());
                return customerRepository.save(newCustomer);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Âge minimum : 18 ans"
                );
            }
        }
    }

    @PostMapping("/all")
    public @ResponseBody List<Customer> addMultipleCustomers (@RequestBody List<Customer> customers, @RequestBody Customer customer) {
        checkLicense(customer.getLicenceNumber());

        for (Customer newcustomer : customers) {
            customerRepository.save(newcustomer);
        }

        return customers;
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
        checkLicense(customer.getLicenceNumber());
        return customerRepository.save(customer);
    }

    // suppression d'un client à l'id
    @ApiOperation("Méthode pour supprimer un client")
    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        return customerRepository.deleteById(id);
    }

    public boolean isCustomerExistent (String licenceNumber) {
        if (customerRepository.getLicenceNumber(licenceNumber) == null) {
            return false;
        } else {
            if (customerRepository.getLicenceNumber(licenceNumber).equals(licenceNumber)) {
                return true;
            } else {
                return false;
            }
        }
    }

    // méthode pour vérifier le numéro de permis de conduire
    public void checkLicense (String drivingLicence) {
        RestTemplate restTemplate = new RestTemplate();
        Boolean isValid = restTemplate.getForObject("http://localhost:9089/licenses/" + drivingLicence, Boolean.class);
        if (Boolean.FALSE.equals(isValid)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Driving Licence Invalid"
            );
        }
    }

    public boolean isAgeValid(Date birthDate) {
        Date currentDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int date1 = Integer.parseInt(formatter.format(birthDate));
        int date2 = Integer.parseInt(formatter.format(currentDate));
        int age = (date2 - date1) / 10000;

        if(age >= 18) {
            return true;
        } else {
            return false;
        }
    }
}
