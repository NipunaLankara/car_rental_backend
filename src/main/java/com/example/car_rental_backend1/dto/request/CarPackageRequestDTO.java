package com.example.car_rental_backend1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarPackageRequestDTO {

    private String packageDuration;
    private int price;
    private int distanceLimitKm;
    private int typeId;
}
