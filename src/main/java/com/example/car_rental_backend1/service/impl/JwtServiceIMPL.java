package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.LoginRequestDTO;
import com.example.car_rental_backend1.dto.response.LoginResponseDTO;
import com.example.car_rental_backend1.entity.User;
import com.example.car_rental_backend1.repo.UserRepo;
import com.example.car_rental_backend1.service.JwtService;
import com.example.car_rental_backend1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class JwtServiceIMPL implements JwtService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(userName);       // my user name is == email

        if (user !=null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthority(user)
            );

        } else {
            throw new UsernameNotFoundException("User Not Found with User Name : "+ userName);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
        return authorities;
    }

    @Override
    public LoginResponseDTO createJwtTokenAndLogin(LoginRequestDTO loginRequestDTO) throws Exception {
        String userEmail = loginRequestDTO.getUserEmail();
        String password = loginRequestDTO.getPassword();

        authenticateUserNameAndPassword(userEmail,password);

        UserDetails userDetails = loadUserByUsername(userEmail);
        String generatedToken = jwtUtil.generateToken(userDetails);

        User user = userRepo.findByEmail(userEmail);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                user,generatedToken
        );
        return loginResponseDTO;
    }

    private void authenticateUserNameAndPassword(String userEmail, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail,password));

        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials",e);
        }
    }
}
