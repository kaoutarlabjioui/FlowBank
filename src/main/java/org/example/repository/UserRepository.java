package org.example.repository;

import org.example.model.User;

import java.util.HashSet;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    Optional<User> findByEmail(String email);
    HashSet<User> all();


}
