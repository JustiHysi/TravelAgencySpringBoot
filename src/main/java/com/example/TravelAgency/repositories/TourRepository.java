package com.example.TravelAgency.repositories;

import com.example.TravelAgency.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TourRepository extends JpaRepository<Tour,Long> {
    Optional<Tour> findByName(String name);
}