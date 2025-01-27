package com.example.car_rental_backend1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "driver")
public class Driver {
    @Id
    @Column(name = "driver_id",length = 250,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",length = 300,nullable = false)
    private String name;

    @Column(name = "address",length = 350,nullable = false)
    private String address;

    @Column(name = "license_number",length = 150,nullable = false,unique = true)
    private  String licenseNumber;

    @Column(name = "phone_number",length = 10,nullable = false)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name="car_id", nullable=false)
    private Car carId;



}
