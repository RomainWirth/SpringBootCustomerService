package com.customer.microcustomerservice.dao;

import com.customer.microcustomerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerDaoImplement implements CustomerDao {
    public static ArrayList<Customer> customerList = new ArrayList<>(List.of(
            new Customer(1, "D'Artagnan", "Charles", new Date(), "13AA00001"),
            new Customer(2, "de Sillègue d'Athos d'Autevielle", "Armand", new Date(), "14AA00001"),
            new Customer(3, "de Portos", "Isaac", new Date(), "15AA00001"),
            new Customer(4, "d'Aramis", "Henri", new Date(), "16AA00001")
    ));

    @Override
    public ArrayList<Customer> findAll() {
        return customerList;
    }
    @Override
    public Customer findById(int id) {
        for (Customer customer : customerList) { // boucle for sur le arraylist customerList
            if (id == customer.getId()) { // comparaison de l'id passée dans l'URL avec l'id de chaque client de customerList
                return customer; // renvoie le customer correspondant
            }
        }
        return null; // sinon ne renvoie rien
    }

    @Override
    public Customer save(Customer customer) {
        customerList.add(customer);
        return customer;
    }
}
