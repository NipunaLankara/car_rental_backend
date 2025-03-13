package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.request.CarTypeRequestDTO;
import com.example.car_rental_backend1.dto.response.CarTypeResponseDTO;
import com.example.car_rental_backend1.service.CarTypeService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all-car-types")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getAllCarTypes() {
       List<CarTypeResponseDTO> carTypeResponseDTOS= carTypeService.getAllCarTypes();

       return new ResponseEntity<StandardResponse>(
               new StandardResponse(200,"ALl Car Type List",carTypeResponseDTOS),
               HttpStatus.OK
       );
    }

    @PutMapping(
            path = "/update-car-type"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> updateCarType(@RequestBody CarTypeRequestDTO carTypeRequestDTO) {
       String message = carTypeService.updateCarType(carTypeRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Update Car Type Response",message),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "/delete-car-type-by-id",
            params = "id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> deleteCarTypeById (@RequestParam (value = "id") int id) {
        String message = carTypeService.deleteCarTypeById(id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Delete Car Type Response",message),
                HttpStatus.OK
        );
    }

}
