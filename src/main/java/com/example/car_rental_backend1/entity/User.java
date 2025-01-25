package com.example.car_rental_backend1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "user")

public class User {
    @Id
    @Column(name = "user_id",length = 255)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "first_name",length = 200,nullable = false)
    private String firstName;

    @Column(name = "lsat_name",length = 200)
    private String lastName;

    @Column(name = "address",length = 270,nullable = false)
    private String address;

    @Column(name = "email",length = 200,nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "nic",nullable = false)
    private String nic;

    @Column(name = "password",length = 300, nullable = false)
    private String password;

    @Column(name = "user_role",length = 150,nullable = false)
    private String userRole;

    public User(String firstName, String lastName, String address, String email, String phoneNumber, String nic, String password, String userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
        this.password = password;
        this.userRole = userRole;
    }


}
