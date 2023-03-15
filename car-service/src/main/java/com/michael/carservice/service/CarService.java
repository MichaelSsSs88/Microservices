package com.michael.carservice.service;

import com.michael.carservice.entity.Car;

import java.util.Set;

public interface CarService {
    public Set<Car> findAll();

    public Car save(Car car);
    public  Car findByBrand(String name);
    public  Car findById(Long id);
    public Car updateById(Long id, Car car);
    public  Set<Car> findByUserId(Long id);

    public void deleteById(Long id);
}
