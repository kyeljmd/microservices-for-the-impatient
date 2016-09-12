package org.brightworks.cmm.api.resource;

import org.brightworks.cmm.api.domain.Customer;
import org.brightworks.cmm.api.repository.CustomerRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kyel on 9/12/2016.
 */
@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/customer/all")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}
