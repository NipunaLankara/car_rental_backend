package com.example.car_rental_backend1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserSaveDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String nic;
    private String password;
    private String userRole;

}
