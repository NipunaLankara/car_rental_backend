package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.paginate.PaginateBookindAndBillResponseDTO;
import com.example.car_rental_backend1.dto.paginate.PaginateBookingResponseDTO;
import com.example.car_rental_backend1.dto.request.BookingRequestDTO;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(
            path = "/get-all-bookings",
            params = {"page"}
    )
    public ResponseEntity<StandardResponse> getAllBookings(@RequestParam (value = "page") int page) {
       PaginateBookingResponseDTO paginateBookingResponseDTO = bookingService.getAllBookings(page);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Booking Details List With Paginate", paginateBookingResponseDTO),
                HttpStatus.OK
        );

        return response;

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(
            path = "/get-all-booking-for-user",
            params = {"id","page"}
    )
    public ResponseEntity<StandardResponse> getAllBookingForUser(
            @RequestParam (value = "id") int id,
            @RequestParam (value = "page") int page

    ) {
        PaginateBookingResponseDTO paginateBookingResponseDTO = bookingService.getAllBookingForUser(id,page);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Booking Details List For Custoer With Paginate", paginateBookingResponseDTO),
                HttpStatus.OK
        );

        return response;

    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(
            path = "/get-booking-and-bill-for-booking-id", // parse the booking id
            params = {"id","page"}
    )
    public ResponseEntity<StandardResponse> getBookingDetailsAndBillDetailsByBookingId(
            @RequestParam (value = "id") int id,
            @RequestParam (value = "page") int page

    ) {
        PaginateBookindAndBillResponseDTO paginateBookindAndBillResponseDTO = bookingService.getBookingDetailsAndBillDetailsByBookingId(id,page);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "All Booking Details And Bill Details List  With Paginate", paginateBookindAndBillResponseDTO),
                HttpStatus.OK
        );

        return response;

    }





}
