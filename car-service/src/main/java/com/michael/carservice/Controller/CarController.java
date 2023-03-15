package com.michael.carservice.Controller;

import com.michael.carservice.entity.Car;
import com.michael.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @GetMapping("/")
    public ResponseEntity<Set<Car>> findAll(){
        return new ResponseEntity<>(this.carService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Car> save(@RequestBody Car car){
        return new ResponseEntity<>(this.carService.save(car), HttpStatus.OK);
    }

    @GetMapping(    "/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.carService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<Car> findById(@PathVariable String brand){
        return new ResponseEntity<>(this.carService.findByBrand(brand), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<Car>> findByCar(@PathVariable Long id){
        return new ResponseEntity<>(this.carService.findByUserId(id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
