package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.CarTypeRequestDTO;
import com.example.car_rental_backend1.entity.CarType;
import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.repo.CarTypeRepo;
import com.example.car_rental_backend1.service.CarTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarTypeServiceIMPL implements CarTypeService {
    @Autowired
    private CarTypeRepo carTypeRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String addNewCarType(CarTypeRequestDTO carTypeRequestDTO) {

        if (validateDetails(carTypeRequestDTO)) {

            if (!carTypeRepo.existsByTypeName(carTypeRequestDTO.getTypeName())) {

                CarType carType = modelMapper.map(carTypeRequestDTO, CarType.class);
                carTypeRepo.save(carType);

                return "Car Type " + carTypeRequestDTO.getTypeName() + " Added Successfully";
            } else {
                throw new AlreadyExistsException("Car Type " + carTypeRequestDTO.getTypeName() + " Already Exists");
            }
        } else {
            throw new IllegalArgumentException("Something Wrong");
        }
    }

    private boolean validateDetails(CarTypeRequestDTO carTypeRequestDTO) {
        String typeName = carTypeRequestDTO.getTypeName();
        int chargePerDay = carTypeRequestDTO.getChargePerDay();
        int chargePerKm = carTypeRequestDTO.getChargePerKm();

        if (typeName.isEmpty() || String.valueOf(chargePerDay).isEmpty() || String.valueOf(chargePerKm).isEmpty()) {
            throw new IllegalArgumentException("Values Can not be Empty");
        }
        return true;

    }
}
