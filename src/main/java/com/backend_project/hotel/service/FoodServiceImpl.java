package com.backend_project.hotel.service;

import com.backend_project.hotel.model.FoodModel;
import com.backend_project.hotel.model.HotelModel;
import com.backend_project.hotel.repositories.FoodRepository;
import com.backend_project.hotel.repositories.HotelRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private HotelRepositories hotelRepository;

    @Override
    public FoodModel addFood(FoodModel food, Integer staffId) {
        Optional<HotelModel> staff = hotelRepository.findById(staffId);
        if (staff.isEmpty()) {
            throw new RuntimeException("Staff not found");
        }
        food.setStaff(staff.get());
        food.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return foodRepository.save(food);
    }

    @Override
    public List<FoodModel> getFoodsByStaff(Integer staffId) {
        return foodRepository.findByStaffStaffId(staffId);
    }

    @Override
    public FoodModel getFoodById(Integer foodId) {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
    }

    @Override
    public FoodModel updateFood(Integer foodId, FoodModel food, Integer staffId) {
        FoodModel existingFood = getFoodById(foodId);
        if (!existingFood.getStaff().getStaffId().equals(staffId)) {
            throw new RuntimeException("You are not authorized to update this food item");
        }
        existingFood.setName(food.getName());
        existingFood.setPrice(food.getPrice());
        existingFood.setImage(food.getImage());
        existingFood.setDescription(food.getDescription());
        existingFood.setRecipe(food.getRecipe());
        existingFood.setIsAvailable(food.getIsAvailable());
        return foodRepository.save(existingFood);
    }

    @Override
    public void deleteFood(Integer foodId, Integer staffId) {
        FoodModel food = getFoodById(foodId);
        if (!food.getStaff().getStaffId().equals(staffId)) {
            throw new RuntimeException("You are not authorized to delete this food item");
        }
        foodRepository.deleteById(foodId);
    }

    @Override
    public FoodModel toggleAvailability(Integer foodId, Integer staffId) {
        FoodModel food = getFoodById(foodId);
        if (!food.getStaff().getStaffId().equals(staffId)) {
            throw new RuntimeException("You are not authorized to update this food item");
        }
        food.setIsAvailable(!food.getIsAvailable());
        return foodRepository.save(food);
    }

    @Override
    public List<FoodModel> getAvailableFoods() {
        return foodRepository.findByIsAvailableTrue();
    }
    @Override
    public List<FoodModel> getAllFoods() {
        return foodRepository.findAll();
    }
}