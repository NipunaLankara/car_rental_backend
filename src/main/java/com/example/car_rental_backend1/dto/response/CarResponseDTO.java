package com.example.car_rental_backend1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

@AllArgsConstructor
@NamedEntityGraph
@Data
public class CarResponseDTO {

    private String carNumber;
    private String model;
    private String type;
    private String status;
}
