package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.paginate.PaginateBookingResponseDTO;
import com.example.car_rental_backend1.dto.request.BookingRequestDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;

public interface BookingService {
    String addNewBooking(BookingRequestDTO bookingRequestDTO );

    PaginateBookingResponseDTO getAllBookings(int page);

    PaginateBookingResponseDTO getAllBookingForUser(int id, int page);
}
