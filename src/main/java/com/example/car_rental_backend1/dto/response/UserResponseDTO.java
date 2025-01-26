package com.example.car_rental_backend1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String nic;
    private String password;
}
