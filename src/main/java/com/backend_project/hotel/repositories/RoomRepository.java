package com.backend_project.hotel.repositories;

import com.backend_project.hotel.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomModel, Integer> {
    List<RoomModel> findByCreatedByStaffId(Integer staffId);
    List<RoomModel> findByIsAvailableTrue();
    boolean existsByRoomNumber(String roomNumber);  // Add this method
    Optional<RoomModel> findByRoomNumber(String roomNumber);  // Useful for other operations
}