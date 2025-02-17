package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.BookingRequestDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;

public interface BookingService {
    String addNewBooking(BookingRequestDTO bookingRequestDTO );
}
