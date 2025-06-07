package com.backend_project.hotel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend_project.hotel.model.HotelModel;

@Repository
public interface HotelRepositories extends JpaRepository<HotelModel, Integer> {
	Optional<HotelModel> findByUsernameAndPassword(String username, String password);
	Optional<HotelModel> findByEmail(String email);
	
}