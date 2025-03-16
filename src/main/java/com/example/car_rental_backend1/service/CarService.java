package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.paginate.PaginateCarResponseDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.response.CarResponseDTO;

import java.util.List;

public interface CarService {
    String addNewCar(CarRequestDTO carRequestDTO);

    List<CarResponseDTO> getAllCars();

    List<CarResponseDTO> getAllCarsByStatus(String status);


    String updateCar(CarRequestDTO carRequestDTO);

    String deleteCarByCarNumber(String carNumber);

    PaginateCarResponseDTO getAllCarsWithPaginate(int page, int size);

    PaginateCarResponseDTO getCarByStatusWithPaginate(String status, int page);

    CarResponseDTO getCarByCaNumber(String carNumber);
}
