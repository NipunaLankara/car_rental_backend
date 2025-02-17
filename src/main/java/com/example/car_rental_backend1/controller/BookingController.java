package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.BookingRequestDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.service.BookingService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/booking")
@CrossOrigin

public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-new-booking")
    public ResponseEntity<StandardResponse> addNewBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        String message = bookingService.addNewBooking(bookingRequestDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Create New Booking", message),
                HttpStatus.CREATED
        );
        return response;

    }
}
