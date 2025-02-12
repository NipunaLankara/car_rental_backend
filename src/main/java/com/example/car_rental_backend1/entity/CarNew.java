package com.example.car_rental_backend1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "car_new")

public class CarNew {

    @Id
    @Column(name = "car_number",length = 280,nullable = false, unique = true)
    private String carNumber;

    @Column(name = "model",length = 280,nullable = false)
    private String model;

    @Column(name = "status",length = 250,nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private CarType carType;

    @OneToOne(mappedBy="carNumber")
    private Driver driver;

    @OneToMany(mappedBy = "carNumber")
    private Set<Bookings> bookings;



}
