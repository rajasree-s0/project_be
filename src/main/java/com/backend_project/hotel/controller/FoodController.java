package com.backend_project.hotel.controller;

import com.backend_project.hotel.model.FoodModel;
import com.backend_project.hotel.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/")
    public ResponseEntity<FoodModel> addFood(@RequestBody FoodModel food, @RequestHeader("staffId") Integer staffId) {
        FoodModel createdFood = foodService.addFood(food, staffId);
        return ResponseEntity.ok(createdFood);
    }
    @GetMapping("/")
    public ResponseEntity<List<FoodModel>> getAllFoods() {
        List<FoodModel> allFoods = foodService.getAllFoods();
        return ResponseEntity.ok(allFoods);
    }

    @GetMapping("/staff")
    public ResponseEntity<List<FoodModel>> getFoodsByStaff(@RequestHeader("staffId") Integer staffId) {
        List<FoodModel> staffFoods = foodService.getFoodsByStaff(staffId);
        return ResponseEntity.ok(staffFoods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodModel> getFoodById(@PathVariable Integer id) {
        FoodModel food = foodService.getFoodById(id);
        return ResponseEntity.ok(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodModel> updateFood(@PathVariable Integer id, @RequestBody FoodModel food, @RequestHeader("staffId") Integer staffId) {
        FoodModel updatedFood = foodService.updateFood(id, food, staffId);
        return ResponseEntity.ok(updatedFood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Integer id, @RequestHeader("staffId") Integer staffId) {
        foodService.deleteFood(id, staffId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<FoodModel> toggleFoodAvailability(@PathVariable Integer id, @RequestHeader("staffId") Integer staffId) {
        FoodModel food = foodService.toggleAvailability(id, staffId);
        return ResponseEntity.ok(food);
    }

    @GetMapping("/available")
    public ResponseEntity<List<FoodModel>> getAvailableFoods() {
        List<FoodModel> availableFoods = foodService.getAvailableFoods();
        return ResponseEntity.ok(availableFoods);
    }
}