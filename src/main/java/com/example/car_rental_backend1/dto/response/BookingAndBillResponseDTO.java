package com.example.car_rental_backend1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingAndBillResponseDTO {

    private Date bookingDate;
    private Date startDate;
    private Date returnDate;
    private long startMillage;
    private long endMillage;
    private int userId;
    private String carNumber;
    private int carPackageId;
    private String packageDuration;

    // Billing Details
    private double packagePrice;
    private double addtionalChargePerKm;
    private double addtionalChargePerDay;
    private double deposit;
    private double discount;
    private double total;
    private double prepayment;
    private double balance;

    public BookingAndBillResponseDTO(Date bookingDate, Date startDate, Date returnDate, long startMillage, long endMillage, int userId, String carNumber, String packageDuration, double packagePrice, double addtionalChargePerKm, double addtionalChargePerDay, double deposit, double discount, double total, double prepayment, double balance) {
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.startMillage = startMillage;
        this.endMillage = endMillage;
        this.userId = userId;
        this.carNumber = carNumber;
        this.packageDuration = packageDuration;
        this.packagePrice = packagePrice;
        this.addtionalChargePerKm = addtionalChargePerKm;
        this.addtionalChargePerDay = addtionalChargePerDay;
        this.deposit = deposit;
        this.discount = discount;
        this.total = total;
        this.prepayment = prepayment;
        this.balance = balance;
    }




}
