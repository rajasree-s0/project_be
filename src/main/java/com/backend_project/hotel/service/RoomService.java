package com.backend_project.hotel.service;

import com.backend_project.hotel.model.RoomModel;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RoomService {
    ResponseEntity<?> addRoom(RoomModel model, Integer staffId);
    ResponseEntity<List<RoomModel>> getAllRooms();
    ResponseEntity<List<RoomModel>> getAvailableRooms();
    ResponseEntity<List<RoomModel>> getRoomsByManager(Integer managerId);
    ResponseEntity<?> updateRoom(Integer id, RoomModel model);
    ResponseEntity<Void> deleteRoom(Integer id);
    ResponseEntity<?> getRoomById(Integer id);
}