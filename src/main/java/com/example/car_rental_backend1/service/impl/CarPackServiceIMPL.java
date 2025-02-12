package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.CarPackageRequestDTO;
import com.example.car_rental_backend1.entity.CarPackage;
import com.example.car_rental_backend1.repo.CarPackRepo;
import com.example.car_rental_backend1.service.CarPackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarPackServiceIMPL implements CarPackService {
    @Autowired
    private CarPackRepo carPackRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public String addNewCarPackage(CarPackageRequestDTO carPackageRequestDTO) {

        if (carPackageRequestDTO.toString() != null) {

            CarPackage carPackage = modelMapper.map(carPackageRequestDTO,CarPackage.class);
            carPackRepo.save(carPackage);
            return "Car Package "+ carPackageRequestDTO.getPackageDuration()+" added Successfully";

        } else {
            throw new IllegalArgumentException("Invalid value");
        }


    }
}
