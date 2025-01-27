package com.example.car_rental_backend1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class DriverRequestDTO {

    private int id;
    private String name;
    private String address;
    private String licenseNumber;
    private String phoneNumber;
    private int carId;

    public DriverRequestDTO(String name, String address, String licenseNumber, String phoneNumber, int carId) {
        this.name = name;
        this.address = address;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.carId = carId;
    }
}
