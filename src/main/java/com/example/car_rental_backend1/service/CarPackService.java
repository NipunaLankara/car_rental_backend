package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.CarPackageRequestDTO;

public interface CarPackService {
    String addNewCarPackage(CarPackageRequestDTO carPackageRequestDTO);
}
