package org.brightworks.cmm.api.repository;

import org.brightworks.cmm.api.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by kyel on 9/11/2016.
 */

@RestResource(rel = "customer", path = "customer")
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>{

    List<Customer> findAll();
}
