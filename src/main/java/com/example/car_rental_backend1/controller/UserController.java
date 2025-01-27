package com.example.car_rental_backend1.controller;

import com.example.car_rental_backend1.dto.request.LoginRequestDTO;
import com.example.car_rental_backend1.dto.request.UserSaveDTO;
import com.example.car_rental_backend1.dto.response.LoginResponseDTO;
import com.example.car_rental_backend1.dto.response.UserResponseDTO;
import com.example.car_rental_backend1.service.JwtService;
import com.example.car_rental_backend1.service.UserService;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register-new-user")
    public ResponseEntity<StandardResponse> registerNewUser(@RequestBody UserSaveDTO userSaveDTO) throws Exception {
        String message = userService.registerNewUser(userSaveDTO);

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "User Saved", message), HttpStatus.CREATED
        );
        return response;

    }

    @PostMapping("/authenticate")
    public LoginResponseDTO createJwtTokenAndLogin(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        return jwtService.createJwtTokenAndLogin(loginRequestDTO);

    }

    @GetMapping("/get-all-customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<UserResponseDTO> userResponseDTOList = userService.getAllCustomers();

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Customer List", userResponseDTOList),
                HttpStatus.OK
        );
        return response;


    }

    @DeleteMapping(
            path = "/delete-customer",
            params = "id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam(value = "id") int id) {
        String message = userService.deleteCustomer(id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "User Deleted", message),
                HttpStatus.CREATED
        );
    }


}