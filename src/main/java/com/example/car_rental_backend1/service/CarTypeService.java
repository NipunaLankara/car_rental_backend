package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.CarTypeRequestDTO;
import com.example.car_rental_backend1.dto.response.CarTypeResponseDTO;

import java.util.List;

public interface CarTypeService {

    String addNewCarType(CarTypeRequestDTO carTypeService);

    List<CarTypeResponseDTO> getAllCarTypes();

    String updateCarType(CarTypeRequestDTO carTypeRequestDTO);

    String deleteCarTypeById(int id);

    CarTypeResponseDTO getCarTypeById(int id);
}
