package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.DriverRequestDTO;

public interface DriverService {
    String addNewDriver(DriverRequestDTO driverRequestDTO);
}
