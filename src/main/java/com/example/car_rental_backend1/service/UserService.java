package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.UserSaveDTO;
import com.example.car_rental_backend1.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    String registerNewUser(UserSaveDTO userSaveDTO) throws Exception;

    List<UserResponseDTO> getAllCustomers();
}
