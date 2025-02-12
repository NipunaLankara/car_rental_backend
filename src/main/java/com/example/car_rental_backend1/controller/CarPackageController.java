package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.CarPackageRequestDTO;
import com.example.car_rental_backend1.service.CarPackService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car_package")
@CrossOrigin

public class CarPackageController {
    @Autowired
    private CarPackService carPackService;


    @PostMapping("/add-new-car-package")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> addNewCarPackage (@RequestBody CarPackageRequestDTO carPackageRequestDTO) {
        String message = carPackService.addNewCarPackage(carPackageRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Car Package Saved", message),
                HttpStatus.CREATED
        );
        return response;

    }


}
