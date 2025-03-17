package com.example.car_rental_backend1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingResponseDTO {

    private int bookingId;
    private Date bookingDate;
    private Date startDate;
    private Date returnDate;
    private long startMillage;
    private long endMillage;
    private int userId;
    private String carNumber;
    private int carPackageId;

    private String packageDuration;

    public BookingResponseDTO(int bookingId, Date bookingDate, Date startDate, Date returnDate, long startMillage, long endMillage, int userId, String carNumber, int carPackageId) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.startMillage = startMillage;
        this.endMillage = endMillage;
        this.userId = userId;
        this.carNumber = carNumber;
        this.carPackageId = carPackageId;
    }

    public BookingResponseDTO(int bookingId, Date bookingDate, Date startDate, Date returnDate, long startMillage, long endMillage, String carNumber, String packageDuration) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.startMillage = startMillage;
        this.endMillage = endMillage;
        this.carNumber = carNumber;
        this.packageDuration = packageDuration;
    }
}
