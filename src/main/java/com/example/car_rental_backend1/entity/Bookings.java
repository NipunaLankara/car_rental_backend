package com.example.car_rental_backend1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@Table(name = "booking")
public class Bookings {

    @Id
    @Column(name = "booking_id",length = 280,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "booking_status",length = 280,nullable = false)
    private String status;

    @Column(name = "start_date",length = 280,nullable = true)
    private Date startDate;

    @Column(name = "return_date",length = 280,nullable = true)
    private Date returnDate;

    @Column(name = "start_millage",length = 280,nullable = true)
    private long startMillage;

    @Column(name = "end_millage",length = 280,nullable = true)
    private long endMillage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarNew carNumber;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private CarPackage carPackage;

    @OneToOne(mappedBy="bookingId")
    private Bill bill;




}
