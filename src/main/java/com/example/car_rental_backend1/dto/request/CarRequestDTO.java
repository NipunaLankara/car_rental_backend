package com.example.car_rental_backend1.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarRequestDTO {
    private int carId;
    private String carNumber;
    private String model;
    private String type;
    private String status;

    public CarRequestDTO(String carNumber, String model, String type, String status) {
        this.carNumber = carNumber;
        this.model = model;
        this.type = type;
        this.status = status;
    }
}
