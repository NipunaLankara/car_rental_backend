package com.example.car_rental_backend1.dto.paginate;

import com.example.car_rental_backend1.dto.response.CarResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginateCarResponseDTO {
    List<CarResponseDTO> carResponseDTOList;
    private long dataCount;
}
