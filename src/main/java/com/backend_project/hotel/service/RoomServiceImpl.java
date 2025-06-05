package com.backend_project.hotel.service;

import com.backend_project.hotel.model.*;
import com.backend_project.hotel.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {
    
    @Autowired private RoomRepository roomRepository;
    @Autowired private HotelRepositories hotelRepository;

    @Override
    public ResponseEntity<?> addRoom(RoomModel room, Integer staffId) {
        if (staffId == null) {
            return ResponseEntity.badRequest().body("Staff ID cannot be null");
        }
        return hotelRepository.findById(staffId)
            .map(staff -> {
                if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
                    return ResponseEntity.badRequest().body("Room number already exists");
                }
                room.setCreatedBy(staff);
                return ResponseEntity.ok(roomRepository.save(room));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<RoomModel>> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @Override
    public ResponseEntity<List<RoomModel>> getAvailableRooms() {
        return ResponseEntity.ok(roomRepository.findByIsAvailableTrue());
    }

    @Override
    public ResponseEntity<List<RoomModel>> getRoomsByManager(Integer managerId) {
        if (managerId == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(roomRepository.findByCreatedByStaffId(managerId));
    }

    @Override
    public ResponseEntity<?> updateRoom(Integer id, RoomModel model) {
        return roomRepository.findById(id)
            .map(room -> {
                if (!room.getRoomNumber().equals(model.getRoomNumber()) && 
                    roomRepository.existsByRoomNumber(model.getRoomNumber())) {
                    return ResponseEntity.badRequest().body("Room number already exists");
                }
                room.setRoomNumber(model.getRoomNumber());
                room.setRoomType(model.getRoomType());
                room.setPrice(model.getPrice());
                room.setAcType(model.getAcType());
                room.setIsAvailable(model.getIsAvailable());
                room.setImageUrl(model.getImageUrl());
                return ResponseEntity.ok(roomRepository.save(room));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteRoom(Integer id) {
        return roomRepository.findById(id)
            .map(room -> {
                roomRepository.delete(room);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> getRoomById(Integer id) {
        return roomRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}