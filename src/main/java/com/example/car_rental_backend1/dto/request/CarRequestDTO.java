package com.example.car_rental_backend1.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarRequestDTO {

    private String carNumber;
    private String model;
    private String type;
    private String status;
    private String amount;


}
