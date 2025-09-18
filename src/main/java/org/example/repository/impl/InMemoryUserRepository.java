package org.example.repository.impl;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;


public class InMemoryUserRepository implements UserRepository {
    public static InMemoryUserRepository instance =null;

    private InMemoryUserRepository() {}
    public static InMemoryUserRepository getInstance() {
        if (instance == null) {
           return instance = new InMemoryUserRepository();
        }
        return instance;
    }

   public static  HashSet<User> users = new HashSet<User>();
    public  void save(User user){
        users.add(user);
    }
    public Optional<User> findByEmail(String email){
        return users.stream().filter(user->user.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    public HashSet<User> all(){
        return  users;
    }

    public static void update(User user,String newHashedPassword){
            users.stream()
                    .filter(u->u.getId().equals(user.getId()))
                    .findFirst()
                    .ifPresent(u->u.setPassword(newHashedPassword));
    }


}
