package com.example.car_rental_backend1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarPackageResponseDTO {
    private int id;
    private String packageDuration;
    private int distanceLimitKm;
    private int price;
    private int carTypeId;
}
