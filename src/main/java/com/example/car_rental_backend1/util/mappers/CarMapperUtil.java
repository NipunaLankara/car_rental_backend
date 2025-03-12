package com.example.car_rental_backend1.util.mappers;

import com.example.car_rental_backend1.entity.CarNew;
import org.mapstruct.Named;

public class CarMapperUtil {
    @Named("mapCarNewToString")
    public static String mapCarNewToString(CarNew car) {
        return (car != null) ? car.getCarNumber() : null;
    }
}
