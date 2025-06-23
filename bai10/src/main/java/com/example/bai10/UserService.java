package com.example.bai10;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
}
