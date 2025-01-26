package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.response.CarResponseDTO;
import com.example.car_rental_backend1.dto.response.UserResponseDTO;
import com.example.car_rental_backend1.service.CarService;
import com.example.car_rental_backend1.service.UserService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class AdminController {
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    @GetMapping("/home-page")
//    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "this url Admin only";
    }

//     Controllers For Customer........................................................

    @GetMapping("/get-all-customers")
    public  ResponseEntity<StandardResponse> getAllCustomers(){
      List<UserResponseDTO> userResponseDTOList =  userService.getAllCustomers();

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Customer List", userResponseDTOList),
                HttpStatus.OK
        );
        return response;
        

    }


    //     Controllers For Car  ...............................................................

    @PostMapping("/add-new-car")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> addNewCar(@RequestBody CarRequestDTO carRequestDTO) {
        String message = carService.addNewCar(carRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Car Saved", message),
                HttpStatus.CREATED
        );
        return response;
    }

    @GetMapping("/get-all-cars")
    public ResponseEntity<StandardResponse> getAllCars() {
        List<CarResponseDTO> carsResponseDDTOList = carService.getAllCars();

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Car List", carsResponseDDTOList),
                HttpStatus.OK
        );
        return response;

    }

    @GetMapping(
            path = "/get-car-by-status",
            params = "status"
    )
    public ResponseEntity<StandardResponse> getCarByStatus(@RequestParam(value = "status") String status) {
        List<CarResponseDTO> carResponseDTOList = carService.getAllCarsByStatus(status);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Car List Get By Status", carResponseDTOList),
                HttpStatus.OK
        );
        return response;
    }


    @PutMapping(
            path = "/update-car"
    )
    public ResponseEntity<StandardResponse> updateCar(@RequestBody CarRequestDTO carRequestDTO) {
        String message = carService.updateCar(carRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Updated", message),
                HttpStatus.OK
        );
        return response;
    }

    @DeleteMapping(
            path = "/delete-car-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> deleteCarById (@RequestParam(value = "id") int id) {
       String message =  carService.deleteCarById(id);

       return new ResponseEntity<StandardResponse>(
               new StandardResponse(200,"Deleted",message),
               HttpStatus.OK
       );

    }





//    @PostConstruct
//    public void showCars () {
//        String status = "Available";
//        List<CarResponseDTO> carResponseDTOList = carService.getAllCarsByStatus(status);
//        System.out.println(carResponseDTOList);
//    }


}
