package com.backend_project.hotel.controller;

import com.backend_project.hotel.model.RoomModel;
import com.backend_project.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {

    @Autowired 
    private RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomModel model, 
                                     @RequestHeader("X-Staff-Id") String staffId) {
        try {
            if (staffId == null || staffId.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Staff ID is required in the X-Staff-Id header");
            }
            Integer parsedStaffId = Integer.parseInt(staffId);
            return roomService.addRoom(model, parsedStaffId);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid Staff ID format: must be a number");
        }
    }

    @GetMapping("/all-rooms")
    public ResponseEntity<List<RoomModel>> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomModel>> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @GetMapping("/my-rooms")
    public ResponseEntity<?> getMyRooms(@RequestHeader("X-Staff-Id") String managerId) {
        try {
            if (managerId == null || managerId.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Staff ID is required in the X-Staff-Id header");
            }
            Integer parsedManagerId = Integer.parseInt(managerId);
            System.out.println("Received request for manager ID: " + parsedManagerId);
            ResponseEntity<List<RoomModel>> response = roomService.getRoomsByManager(parsedManagerId);
            System.out.println("Returning " + response.getBody().size() + " rooms");
            return response;
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid Staff ID format: must be a number");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Integer id, 
                                        @RequestBody RoomModel model) {
        return roomService.updateRoom(id, model);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id) {
        return roomService.deleteRoom(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Integer id) {
        return roomService.getRoomById(id);
    }
}