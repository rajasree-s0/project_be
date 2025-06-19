package com.backend_project.hotel.repositories;

import com.backend_project.hotel.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    Optional<CustomerModel> findByUsername(String username);
    Optional<CustomerModel> findByEmail(String email);
}