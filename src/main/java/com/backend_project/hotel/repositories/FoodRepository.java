package com.backend_project.hotel.repositories;

import com.backend_project.hotel.model.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodModel, Integer> {
    List<FoodModel> findByStaffStaffId(Integer staffId);
    List<FoodModel> findByIsAvailableTrue();
}