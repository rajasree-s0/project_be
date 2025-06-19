package com.backend_project.hotel.service;

import com.backend_project.hotel.exception.CustomerAlreadyExistsException;
import com.backend_project.hotel.model.CustomerModel;
import com.backend_project.hotel.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServImpl(CustomerRepository customerRepository, BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerModel saveCustomer(CustomerModel customer) {
        if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
            throw new CustomerAlreadyExistsException("Username already exists: " + customer.getUsername());
        }
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new CustomerAlreadyExistsException("Email already exists: " + customer.getEmail());
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerModel> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }
}
