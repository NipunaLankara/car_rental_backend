package com.example.car_rental_backend1.dto.request;

import com.example.car_rental_backend1.entity.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarPackageRequestDTO {

    private String packageDuration;
    private int price;
    private int typeId;
}
