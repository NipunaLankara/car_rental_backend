package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.DriverRequestDTO;
import com.example.car_rental_backend1.service.DriverService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin


public class DriverController {
    @Autowired
    private DriverService driverService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-new-driver")
    public ResponseEntity<StandardResponse> addNewDriver (@RequestBody DriverRequestDTO driverRequestDTO) {

//        if (driverRequestDTO.getLicenseNumber().isEmpty()) {
//            System.out.println("Driver License is Empty");
//        }

        String message = driverService.addNewDriver(driverRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Driver Saved",message),
                HttpStatus.CREATED
        );
    }




}
