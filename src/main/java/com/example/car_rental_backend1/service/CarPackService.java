package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.CarPackageRequestDTO;
import com.example.car_rental_backend1.dto.response.CarPackageResponseDTO;

import java.util.List;

public interface CarPackService {
    String addNewCarPackage(CarPackageRequestDTO carPackageRequestDTO);

    List<CarPackageResponseDTO> getAllCarPacakge();

    String updateCarPackage(CarPackageRequestDTO carPackageRequestDTO);

    String deleteCarPackageById(int id);
}
