package com.michael.carservice.repository;

import com.michael.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByBrand(String name);
    public Set<Car> findByUserId(Long id);
}
