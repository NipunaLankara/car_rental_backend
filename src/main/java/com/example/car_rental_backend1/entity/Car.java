package com.example.car_rental_backend1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "car")

public class Car {

    @Id
    @Column(name = "car_id",length = 255,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "car_number",length = 280,nullable = false, unique = true)
    private String carNumber;

    @Column(name = "model",length = 280,nullable = false)
    private String model;

    @Column(name = "type",length = 250,nullable = false)
    private String type;

    @Column(name = "status",length = 250,nullable = false)
    private String status;

    public Car(String carNumber, String model, String type, String status) {
        this.carNumber = carNumber;
        this.model = model;
        this.type = type;
        this.status = status;
    }
}
