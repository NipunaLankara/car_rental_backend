package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.CarPackageRequestDTO;
import com.example.car_rental_backend1.dto.request.CarTypeRequestDTO;
import com.example.car_rental_backend1.dto.response.CarPackageResponseDTO;
import com.example.car_rental_backend1.service.CarPackService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all-car-package")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getAllCarPacakge () {
        List<CarPackageResponseDTO> carPackageResponseDTOList = carPackService.getAllCarPacakge();

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Car Packages List", carPackageResponseDTOList),
                HttpStatus.OK
        );
        return response;
    }

    @PutMapping(
            path = "/update-car-package"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> updateCarPackage(@RequestBody CarPackageRequestDTO carPackageRequestDTO) {
        String message = carPackService.updateCarPackage(carPackageRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Update Car Package Response",message),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "/delete-car-package-by-id",
            params = "id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> deleteCarPackageById (@RequestParam (value = "id") int id) {
        String message = carPackService.deleteCarPackageById(id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Delete Car Package Response",message),
                HttpStatus.OK
        );
    }


}
