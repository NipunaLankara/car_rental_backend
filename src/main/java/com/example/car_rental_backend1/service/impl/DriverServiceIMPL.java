package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.DriverRequestDTO;
import com.example.car_rental_backend1.entity.Driver;
import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.repo.DriverRepo;
import com.example.car_rental_backend1.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceIMPL implements DriverService {
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addNewDriver(DriverRequestDTO driverRequestDTO) {

        if (validateDriverSaveRequest(driverRequestDTO)) {

            if (!driverRepo.existsByLicenseNumber(driverRequestDTO.getLicenseNumber())) {

                Driver driver = modelMapper.map(driverRequestDTO, Driver.class);
                driverRepo.save(driver);

                return "Driver " + driverRequestDTO.getName() + " Added Successfully";

            } else {
                throw new AlreadyExistsException("Already Exits this Driver");
            }

        } else {
            throw new IllegalArgumentException("Something Wrong");
        }
    }

    private boolean validateDriverSaveRequest(DriverRequestDTO driverRequestDTO) {

        String name = driverRequestDTO.getName();
        String address = driverRequestDTO.getAddress();
        String licenseNumber = driverRequestDTO.getLicenseNumber();
        String phoneNumber = driverRequestDTO.getPhoneNumber();
        String carNumber = driverRequestDTO.getCarNumber();

//        String errorMessage = null;

        if (name.isEmpty() || address.isEmpty() || licenseNumber.isEmpty() || phoneNumber.isEmpty() ) {

            throw new IllegalArgumentException("Values Can not be Empty");

        } if (carNumber.isEmpty()) {
            throw new IllegalArgumentException("Car Id Can not be Empty");

        } else {
            return true;
        }
    }
}
