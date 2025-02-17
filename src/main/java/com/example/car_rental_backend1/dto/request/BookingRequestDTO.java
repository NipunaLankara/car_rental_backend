package com.example.car_rental_backend1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingRequestDTO {

    private Date bookingDate;
    private Date startDate;
    private Date returnDate;
    private long startMillage;
    private long endMillage;
    private int userId;
    private String carNumber;
    private int carPackageId;


    // Billing Details
    private double packagePrice;
    private double addtionalChargePerKm;
    private double addtionalChargePerDay;
    private double deposit;
    private double discount;
    private double total;
    private double prepayment;
    private double balance;


}
