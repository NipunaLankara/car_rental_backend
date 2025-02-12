package com.example.car_rental_backend1.dto.response;

import com.example.car_rental_backend1.entity.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.NamedEntityGraph;

@AllArgsConstructor
@NamedEntityGraph
@Data
public class CarResponseDTO {

    private String carNumber;
    private String model;
    private String status;
    private int typeId;

}
