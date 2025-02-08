package com.danilobml.gamestore.services.interfaces;

import com.danilobml.gamestore.entities.User;

public interface UserService {

    User getUser(Long id);
    User getUserByUsername(String username);
    void saveUser(User user);
    
}
