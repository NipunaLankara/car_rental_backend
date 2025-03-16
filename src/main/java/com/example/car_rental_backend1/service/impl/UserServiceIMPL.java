package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.UserSaveDTO;
import com.example.car_rental_backend1.dto.response.UserResponseDTO;
import com.example.car_rental_backend1.entity.User;
import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.repo.UserRepo;
import com.example.car_rental_backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String registerNewUser(UserSaveDTO userSaveDTO) {

        if (validateRegisterDetails(userSaveDTO)) {
            if (!userRepo.existsByEmail(userSaveDTO.getEmail())) {
                User user = new User(
                        userSaveDTO.getFirstName(),
                        userSaveDTO.getLastName(),
                        userSaveDTO.getAddress(),
                        userSaveDTO.getEmail(),
                        userSaveDTO.getPhoneNumber(),
                        userSaveDTO.getNic(),
                        getEncodedPassword(userSaveDTO.getPassword()),
                        userSaveDTO.getUserRole()
                );
                userRepo.save(user);
                return "User Registered Successfully";

            } else {

                throw new AlreadyExistsException("This Email Already Exists");
            }
        } else {
            throw new IllegalArgumentException("Something  Wrong");
        }
    }

    @Override
    public List<UserResponseDTO> getAllCustomers() {
        String userRole = "USER";
        List<User> userList = userRepo.findAllByUserRoleEquals(userRole);

        if (!userList.isEmpty()) {

            List<UserResponseDTO> userResponseDTOList = new ArrayList<>();

            for (User user : userList) {

                UserResponseDTO userResponseDTO = new UserResponseDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAddress(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getNic(),
                        user.getPassword()
                );
                userResponseDTOList.add(userResponseDTO);
            }
            return userResponseDTOList;

        } else {
            throw new NotContentException("No Customers");
        }
    }

    @Override
    public String deleteCustomer(int id) {

        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);

            return "User Id = "+id+" User Deleted Successfully";

        } else {
            throw new NotContentException("There is no values for User Id = "+id);
        }
    }

    public boolean validateRegisterDetails(UserSaveDTO userSaveDTO) {
        String firstName = userSaveDTO.getFirstName();
        String lastName = userSaveDTO.getLastName();
        String address = userSaveDTO.getAddress();
        String email = userSaveDTO.getEmail();
        String phoneNumber = userSaveDTO.getPhoneNumber();
        String nic = userSaveDTO.getNic();
        String password = userSaveDTO.getPassword();
        String userRole = userSaveDTO.getUserRole();

        String exceptionError = null;


        if (firstName == null || firstName.isEmpty()) {
            exceptionError = "First name cannot be empty.";
        } else if (lastName == null || lastName.isEmpty()) {
            exceptionError = "Last name cannot be empty.";
        } else if (address == null || address.isEmpty()) {
            exceptionError = "Address cannot be empty.";
        } else if (email == null || email.isEmpty()) {
            exceptionError = "Invalid email format.";
        }  else if (nic == null || nic.isEmpty()) {
            exceptionError = "Invalid NIC format.";
        } else if (password == null || password.length() < 5) {
            exceptionError = "Password must be at least 5 characters long.";
        } else if (userRole == null || userRole.isEmpty() ||
                (!userRole.equalsIgnoreCase("ADMIN") && !userRole.equalsIgnoreCase("USER"))) {
            exceptionError = "User role must be either 'ADMIN' or 'USER'.";
        }

        if (exceptionError != null) {
            throw new IllegalArgumentException(exceptionError);
        }
        return true;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}