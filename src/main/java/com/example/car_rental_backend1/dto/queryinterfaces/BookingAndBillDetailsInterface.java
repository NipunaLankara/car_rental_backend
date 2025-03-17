package com.example.car_rental_backend1.dto.queryinterfaces;

import java.util.Date;

public interface BookingAndBillDetailsInterface {
    Date getBookingDate();
    Date getStartDate();
    Date getReturnDate();
    long getStartMillage();
    long getEndMillage();
    int getUserId();
    String getCarNumber();
    int getCarPackageId();
    String getPackageDuration();

    // Billing Details
    double getPackagePrice();
    double getAddtionalChargePerKm();
    double getAddtionalChargePerDay();
    double getDeposit();
    double getDiscount();
    double getTotal();
    double getPrepayment();
    double getBalance();
}
