package com.backend_project.hotel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class FoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_name", nullable = false)
    private String name;

    @Column(name = "food_price", nullable = false)
    private Double price;

    @Column(name = "food_image", nullable = false)
    private String image;

    @Column(name = "food_description", nullable = false)
    private String description;

    @Column(name = "food_recipe")
    private String recipe;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private HotelModel staff;
}