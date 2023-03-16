package com.michael.bikeservice.repository;

import com.michael.bikeservice.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    Bike findByBrand(String name);
    Set<Bike> findByUserId(Long id);
}
