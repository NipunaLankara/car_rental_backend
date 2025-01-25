package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.LoginRequestDTO;
import com.example.car_rental_backend1.dto.response.LoginResponseDTO;

public interface JwtService {
    LoginResponseDTO createJwtTokenAndLogin(LoginRequestDTO loginRequestDTO) throws Exception;
}
