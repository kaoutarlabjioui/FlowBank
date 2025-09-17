package org.example.repository;

import org.example.model.User;

import java.util.HashSet;

public interface UserRepository {

    void save(User user);
    HashSet<User> all();


}
