package com.customer.microcustomerservice.dao;

import com.customer.microcustomerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerDaoImplement implements CustomerDao {
    public static String pattern = "yyyy-MM-dd XXX";
    public static SimpleDateFormat sdfConvervet = new SimpleDateFormat(pattern);

    public static ArrayList<Customer> customerList;

    static {
        try {
            customerList = new ArrayList<>(List.of(
                    new Customer(1, "D'Artagnan", "Charles", sdfConvervet.parse("1613-10-12 +00:00"), "13AA00001"),
                    new Customer(2, "de Sillègue d'Athos d'Autevielle", "Armand", sdfConvervet.parse("1615-02-15 +00:00"), "14AA00001"),
                    new Customer(3, "de Portos", "Isaac", sdfConvervet.parse("1617-02-02 +00:00"), "15AA00001"),
                    new Customer(4, "d'Aramis", "Henri", sdfConvervet.parse("1620-05-28 +00:00"), "16AA00001")
            ));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

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
