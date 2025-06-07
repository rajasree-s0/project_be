package com.backend_project.hotel.service;

import com.backend_project.hotel.model.FoodModel;
import java.util.List;

public interface FoodService {
    FoodModel addFood(FoodModel food, Integer staffId);
    List<FoodModel> getFoodsByStaff(Integer staffId);
    FoodModel getFoodById(Integer foodId);
    FoodModel updateFood(Integer foodId, FoodModel food, Integer staffId);
    void deleteFood(Integer foodId, Integer staffId);
    FoodModel toggleAvailability(Integer foodId, Integer staffId);
    List<FoodModel> getAvailableFoods();
    List<FoodModel> getAllFoods(); // Added
}