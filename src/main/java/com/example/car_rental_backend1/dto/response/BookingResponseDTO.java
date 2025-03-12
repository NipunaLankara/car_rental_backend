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

}
