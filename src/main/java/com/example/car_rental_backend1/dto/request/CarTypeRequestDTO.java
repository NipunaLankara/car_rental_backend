package com.example.car_rental_backend1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarTypeRequestDTO {

    private String typeName;
    private int chargePerKm;
    private int chargePerDay;


}
