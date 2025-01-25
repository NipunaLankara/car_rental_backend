package com.example.car_rental_backend1.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper  () {
        return new ModelMapper();
    }
}
