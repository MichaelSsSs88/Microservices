package com.michael.userservice.feighclients;


import com.michael.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@FeignClient(name = "car-service", url = "http://localhost:8002/car/")
public interface CarFeignClient {

    @PostMapping("/")
    Car save(@RequestBody Car car);

    @GetMapping("/user/{userId}")
    Set<Car> getCars(@PathVariable Long userId);

}
