package com.example.car_rental_backend1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "bill")
public class Bill {
    @Id
    @Column(name = "bill_id", length = 280, nullable = false)
    private int id;

    @Column(name = "package_price", length = 280, nullable = false)
    private double packagePrice;

    @Column(name = "addtional_charge_perKm", length = 280, nullable = true)
    private double addtionalChargePerKm;

    @Column(name = "addtional_charge_perDay", length = 280, nullable = true)
    private double addtionalChargePerDay;

    @Column(name = "discount", length = 280, nullable = false)
    private double discount;

    @Column(name = "total_price", length = 280, nullable = false)
    private double total;

    @OneToOne
    @JoinColumn(name="booking_id", nullable=false)
    private Bookings bookingId;

}
