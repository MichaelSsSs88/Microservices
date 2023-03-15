package com.michael.carservice.serviceimpl;

import com.michael.carservice.entity.Car;
import com.michael.carservice.repository.CarRepository;
import com.michael.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CarServiceImpl  implements CarService {

    private final CarRepository carRepository;
    @Override
    public Set<Car> findAll() {
        return new HashSet<>(this.carRepository.findAll());
    }

    @Override
    public Car save(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public Car findByBrand(String name) {
        return this.carRepository.findByBrand(name);
    }

    @Override
    public Car findById(Long id) {
        return this.carRepository.findById(id).orElseThrow(()->new RuntimeException("The car does not exist"));
    }

    @Override
    public Car updateById(Long id, Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public Set<Car> findByUserId(Long id) {
        return this.carRepository.findByUserId(id);
    }
}
