package com.example.car_rental_backend1.dto.paginate;

import com.example.car_rental_backend1.dto.response.BookingResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaginateBookingResponseDTO {
    List<BookingResponseDTO> bookingResponseDTOS;
    private long dataCount;
}
