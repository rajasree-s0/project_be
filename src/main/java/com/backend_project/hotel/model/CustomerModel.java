package com.backend_project.hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers", schema = "hotel_database")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Integer id;

    @Column(name = "cust_username", nullable = false, unique = true)
    private String username;

    @Column(name = "cust_fname", nullable = false)
    private String fullName;

    @Column(name = "cust_email", nullable = false, unique = true)
    private String email;

    @Column(name = "cust_pass", nullable = false)
    private String password;

    @Column(name = "cust_phno")
    private String phone;

    @Column(name = "cust_address")
    private String address;
}