package com.backend_project.hotel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rooms")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;
    
    @ManyToOne
    @JoinColumn(name = "created_by_staff_id")
    private HotelModel createdBy;
    
    @Column(unique = true, nullable = false)
    private String roomNumber;
    private String roomType;
    private Double price;
    private String acType;
    private Boolean isAvailable;
    private String imageUrl;
}