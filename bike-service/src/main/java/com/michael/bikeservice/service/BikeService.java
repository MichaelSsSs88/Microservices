package com.michael.bikeservice.service;

import com.michael.bikeservice.entity.Bike;


import java.util.Set;

public interface BikeService {
    public Set<Bike> findAll();

    public Bike save(Bike car);
    public  Bike findByBrand(String name);
    public  Bike findById(Long id);
    public  Set<Bike> findByUserId(Long id);
    public Bike updateById(Long id, Bike car);


    public void deleteById(Long id);
}
