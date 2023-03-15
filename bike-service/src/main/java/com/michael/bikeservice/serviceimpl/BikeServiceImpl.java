package com.michael.bikeservice.serviceimpl;

import com.michael.bikeservice.entity.Bike;
import com.michael.bikeservice.repository.BikeRepository;
import com.michael.bikeservice.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;
    @Override
    public Set<Bike> findAll() {
        return new HashSet<>(this.bikeRepository.findAll());
    }

    @Override
    public Bike save(Bike car) {
        return this.bikeRepository.save(car);
    }

    @Override
    public Bike findByBrand(String name) {
        System.out.println(name);
        return this.bikeRepository.findByBrand(name);
    }

    @Override
    public Bike findById(Long id) {
        return this.bikeRepository.findById(id).orElseThrow(()->new RuntimeException("The car does not exist"));
    }

    @Override
    public Set<Bike> findByUserId(Long id) {
        return this.bikeRepository.findByUserId(id);
    }

    @Override
    public Bike updateById(Long id, Bike car) {
        return this.bikeRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        this.bikeRepository.deleteById(id);
    }
}
