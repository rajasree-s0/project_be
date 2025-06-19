package com.backend_project.hotel.service;

import com.backend_project.hotel.model.CustomerModel;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerModel saveCustomer(CustomerModel customer);
    List<CustomerModel> getAllCustomers();
    Optional<CustomerModel> getCustomerById(Integer id);
}
