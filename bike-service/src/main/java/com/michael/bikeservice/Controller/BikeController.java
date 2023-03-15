package com.michael.bikeservice.Controller;

import com.michael.bikeservice.entity.Bike;
import com.michael.bikeservice.service.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bike")
public class BikeController {
    private final BikeService carService;

    @GetMapping("/")
    public ResponseEntity<Set<Bike>> findAll() {
        return new ResponseEntity<>(this.carService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Bike> save(@RequestBody Bike car) {
        return new ResponseEntity<>(this.carService.save(car), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.carService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/byuser/{id}")
    public ResponseEntity<Set<Bike>> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(this.carService.findByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<Bike> findById(@PathVariable String brand) {
        return new ResponseEntity<>(this.carService.findByBrand(brand), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
