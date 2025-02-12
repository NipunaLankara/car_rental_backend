package com.example.car_rental_backend1.dto.response;

import com.example.car_rental_backend1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoginResponseDTO {
    private User user;
    private String jwtToken;

    
}
