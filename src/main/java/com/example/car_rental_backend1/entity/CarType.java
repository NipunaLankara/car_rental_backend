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

@Table(name = "car_type")
public class CarType {
    @Id
    @Column(name = "id",length = 255,nullable = false)
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;

    @Column(name = "type_name",length = 200,nullable = false,unique = true)
    private String typeName;

    @Column(name = "additional_charge_per_km",nullable = false)
    private int chargePerKm;

    @Column(name = "additional_charge_per_day",nullable = false)
    private int chargePerDay;

    @OneToMany(mappedBy="carType")
    private Set<CarNew> carNew;

    @OneToMany(mappedBy="carType")
    private Set<CarPackage> carPackage;

}
