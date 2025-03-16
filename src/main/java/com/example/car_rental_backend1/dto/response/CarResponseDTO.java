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
    private String status;
    private int typeId;
    private String typeName;

    public CarResponseDTO(String carNumber, String model, String status) {
        this.carNumber = carNumber;
        this.model = model;
        this.status = status;
    }

    public CarResponseDTO(String carNumber, String model, String status, int typeId) {
        this.carNumber = carNumber;
        this.model = model;
        this.status = status;
        this.typeId = typeId;
    }
}
