package com.michael.userservice.feighclients;

import com.michael.userservice.model.Bike;
import com.michael.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient(name = "bike-service", url = "http://localhost:8003/bike/")
public interface  BikeFeignClient{

    @PostMapping("/")
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byuser/{userId}")
    Set<Bike> findBikesByUserId(@PathVariable Long userId);

}
