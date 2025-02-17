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
@Table(name = "car_package")
public class CarPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    @Column(name = "id", length = 280, nullable = false)
    private int id;

    @Column(name = "package_duration", length = 280, nullable = false)
    private String packageDuration;

    @Column(name = "distance_limit_km", length = 250, nullable = false)
    private int distanceLimitKm;

    @Column(name = "price", length = 250, nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CarType carType; // Renamed from `typeId`

    @OneToMany(mappedBy="carPackage")
    private Set<Bookings> bookings;



}
