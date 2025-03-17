package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.paginate.PaginateCarResponseDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.response.CarResponseDTO;
import com.example.car_rental_backend1.service.CarService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@CrossOrigin

public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/add-new-car")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> addNewCar(@RequestBody CarRequestDTO carRequestDTO) {
        String message = carService.addNewCar(carRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Car Saved", message),
                HttpStatus.CREATED
        );
        return response;
    }

    @GetMapping("/get-all-cars")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getAllCars() {
        List<CarResponseDTO> carsResponseDDTOList = carService.getAllCars();

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Car List", carsResponseDDTOList),
                HttpStatus.OK
        );
        return response;

    }

    // Get All Car Details Using Paginate
    @GetMapping(
            path = "/get-all-cars-paginate",
            params = {"page","size"}

    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getAllCarsWithPaginate(
            @RequestParam (value = "page") int page,
            @RequestParam (value = "size") int size
    ) {

        PaginateCarResponseDTO paginateCarResponseDTO = carService.getAllCarsWithPaginate(page,size);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Car List", paginateCarResponseDTO),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping(
            path = "/get-car-by-car-number",
            params = "carNumber"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getCarByCaNumber(@RequestParam(value = "carNumber") String carNumber) {
        System.out.println("Car Number = " +carNumber);
        CarResponseDTO carResponseDTO = carService.getCarByCaNumber(carNumber);


        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Get Car By Car Number ", carResponseDTO),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping(
            path = "/get-car-by-status",
            params = "status"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getCarByStatus(@RequestParam(value = "status") String status) {
        List<CarResponseDTO> carResponseDTOList = carService.getAllCarsByStatus(status);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Car List Get By Status", carResponseDTOList),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping(
            path = "/get-car-by-status-paginate",
            params = {"status","page"}
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getCarByStatusWithPaginate(
            @RequestParam(value = "status") String status,
            @RequestParam(value = "page") int page
    ) {

       PaginateCarResponseDTO paginateCarResponseDTO =  carService.getCarByStatusWithPaginate(status,page);

       ResponseEntity<StandardResponse> response = new ResponseEntity <StandardResponse>(
               new StandardResponse(200,"Get Car List By Status With Paginate",paginateCarResponseDTO),
               HttpStatus.OK
       );
       return response;

    }

    @GetMapping(
            path = "/get-car-by-car-type-and-status-paginate",
            params = {"typeId","status","page"}
    )
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<StandardResponse> getCarByCarTypeAndStatusWithPaginate(
            @RequestParam(value = "typeId") int typeId,
            @RequestParam(value = "status") String status,
            @RequestParam(value = "page") int page
    ) {

        PaginateCarResponseDTO paginateCarResponseDTO =  carService.getCarByCarTypeAndStatusWithPaginate(typeId,status,page);

        ResponseEntity<StandardResponse> response = new ResponseEntity <StandardResponse>(
                new StandardResponse(200,"Get Car List By Car Type and Status With Paginate",paginateCarResponseDTO),
                HttpStatus.OK
        );
        return response;

    }



    @PutMapping(
            path = "/update-car"
    )
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<StandardResponse> updateCar(@RequestBody CarRequestDTO carRequestDTO) {
        String message = carService.updateCar(carRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Updated", message),
                HttpStatus.OK
        );
        return response;
    }

    @DeleteMapping(
            path = "/delete-car-by-car-number",
            params = "carNumber"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> deleteCarById(@RequestParam(value = "carNumber") String carNumber) {
        String message = carService.deleteCarByCarNumber(carNumber);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Deleted", message),
                HttpStatus.OK
        );

    }




}
