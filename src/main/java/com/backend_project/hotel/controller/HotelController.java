package com.backend_project.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend_project.hotel.model.HotelModel;
import com.backend_project.hotel.service.HotelService;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody HotelModel loginRequest) {
        return hotelService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
    
    @PostMapping("/restaurant-login")
    public ResponseEntity<?> restaurantLogin(@RequestBody HotelModel loginRequest) {
        return hotelService.restaurantLogin(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/add")
    public ResponseEntity<HotelModel> addStaff(@RequestBody HotelModel model) {
        return hotelService.addStaff(model);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelModel>> getAllStaff() {
        return hotelService.getAllStaff();
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<HotelModel> updateStaff(@PathVariable Integer id, @RequestBody HotelModel model) {
        return hotelService.updateStaff(id, model);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Integer id) {
        return hotelService.deleteStaff(id);
    }
}