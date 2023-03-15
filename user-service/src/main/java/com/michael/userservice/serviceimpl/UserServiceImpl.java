package com.michael.userservice.serviceimpl;

import com.michael.userservice.entity.User;
import com.michael.userservice.feighclients.BikeFeignClient;
import com.michael.userservice.feighclients.CarFeignClient;
import com.michael.userservice.model.Bike;
import com.michael.userservice.repository.UserRepository;
import com.michael.userservice.service.UserService;
import com.michael.userservice.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    private final CarFeignClient carFeignClient;

    private final BikeFeignClient bikeFeignClient;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, CarFeignClient carFeignClient, BikeFeignClient bikeFeignClient) {
        this.userRepository = userRepository;
        //this.restTemplate = restTemplate;
        this.restTemplate = restTemplate;
        this.carFeignClient = carFeignClient;
        this.bikeFeignClient = bikeFeignClient;
    }

    @Override
    public Set<User> findAll() {
        return new HashSet<>(this.userRepository.findAll(Sort.by("name")));
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public User updateById(Long id, User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User save(User user) throws RuntimeException{
        System.out.println(user.getName());
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public Set<Car> getCarList(Long id) {
        return restTemplate.getForObject("http://localhost:8002/car/user/"+id, Set.class);
    }

    public Set<Bike> getBikeList(Long id) {
        return restTemplate.getForObject("http://localhost:8003/bike/byuser/"+id, Set.class);
    }

    public Car save(Long userId,Car car){
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Set<Car> getCars(Long userId){
        return carFeignClient.getCars(userId);
    }

    public Bike save(Long userId,Bike bike){
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Set<Bike> getBikes(Long userId){
        return bikeFeignClient.findBikesByUserId(userId);
    }

    public Map<String, Object> vehiclesByUserId(Long userId){
        Map<String, Object> result= new HashMap<>();
        User user= this.userRepository.findById(userId).orElse(null);
        if(user==null){
            result.put("error", "The user does not exist");
            return result;
        }
        result.put("user", user);
        Set<Bike>bikes=  bikeFeignClient.findBikesByUserId(userId);
        Set<Car>cars= carFeignClient.getCars(userId);
        if (!bikes.isEmpty())result.put("bikes", bikes);
        if(!cars.isEmpty())result.put("cars",cars);
        return result;
    }
}
