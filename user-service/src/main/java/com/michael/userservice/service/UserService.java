package com.michael.userservice.service;

import com.michael.userservice.entity.User;

import java.util.Set;

public interface UserService {
    public Set<User> findAll();
    public User findById(Long id);

    public User updateById(Long id, User user);

    public User save(User user);

    public void deleteById(Long id);

}
