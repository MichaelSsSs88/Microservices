package com.michael.userservice.feighclients;

import com.michael.userservice.model.Bike;
import com.michael.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(name = "bike-service")//, url = "http://localhost:8003/bike/")
public interface  BikeFeignClient{

    @PostMapping("/")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/bike/byuser/{userId}")
    Set<Bike> findBikesByUserId(@PathVariable Long userId);

}
