package com.michael.userservice.controller;

import com.michael.userservice.entity.User;
import com.michael.userservice.model.Bike;
import com.michael.userservice.serviceimpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.michael.userservice.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;



    @PostMapping("/")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        try{
            if(bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<String, String>();
                bindingResult.getAllErrors().forEach(error -> errors.put(error.getCode(),error.getDefaultMessage()));
                return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
            }
            System.out.println(user.getName());
            return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(ex.getCause(), HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Set<User>> getUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/{id}/vehicles")
    public ResponseEntity<Map<String,Object>> getVehiclesByUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.vehiclesByUserId(id), HttpStatus.OK);
    }
    /*@GetMapping("/{id}/car/")
    public ResponseEntity<Set<Car>> getCarUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getCarList(id), HttpStatus.OK);

    }*/

  /*  @GetMapping("/{id}/bike/")
    public ResponseEntity<Set<Bike>> getBikeUser(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getBikeList(id), HttpStatus.OK);

    }*/

    @PostMapping("{userId}/car/")
    public ResponseEntity<Car> saveCar(@PathVariable Long userId, @RequestBody Car car){
        return new ResponseEntity(this.userService.save(userId,car), HttpStatus.OK);
    }
    @GetMapping("{userId}/car/")
    public ResponseEntity<Set<Car>> getCars(@PathVariable Long userId){
        return new ResponseEntity(this.userService.getCars(userId), HttpStatus.OK);
    }

    @PostMapping("{userId}/bike/")
    public ResponseEntity<Bike> saveBike(@PathVariable Long userId, @RequestBody Bike bike){
        return new ResponseEntity(this.userService.save(userId,bike), HttpStatus.OK);
    }

    @GetMapping("{userId}/bike/")
    public ResponseEntity<Set<Bike>> getBikes(@PathVariable Long userId){
        return new ResponseEntity(this.userService.getBikes(userId), HttpStatus.OK);
    }

}
