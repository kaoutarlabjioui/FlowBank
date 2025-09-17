package org.example.model;

import java.util.UUID;

public class User {

    private UUID id;
    private  String fullName;
    private  String email;
    private  String phone;
    private  String address;


    public User(UUID id, String fullName, String email, String phone, String address) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;

    }

    @Override
    public String toString() {
        return ("ID :" + this.id + "\nFullName:" + this.fullName + "\nEmail :" + this.email + "\nPhone : " + this.phone + "\nAddress :" + this.address +) ;
    }
}
