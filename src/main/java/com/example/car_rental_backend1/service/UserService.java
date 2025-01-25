package com.example.car_rental_backend1.service;

import com.example.car_rental_backend1.dto.request.UserSaveDTO;

public interface UserService {
    String registerNewUser(UserSaveDTO userSaveDTO) throws Exception;
}
