package com.example.car_rental_backend1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarTypeResponseDTO {
    private int id;
    private String typeName;
    private int chargePerKm;
    private int chargePerDay;
}
