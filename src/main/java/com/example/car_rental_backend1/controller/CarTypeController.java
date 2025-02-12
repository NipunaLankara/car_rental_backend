package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.request.CarTypeRequestDTO;
import com.example.car_rental_backend1.service.CarTypeService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/car_type")
@CrossOrigin

public class CarTypeController {
    @Autowired
    private CarTypeService carTypeService;

    @PostMapping("/add-new-type")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> addNewCar(@RequestBody CarTypeRequestDTO carTypeRequestDTO) {
        String message = carTypeService.addNewCarType(carTypeRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Car Saved", message),
                HttpStatus.CREATED
        );
        return response;
    }
}
