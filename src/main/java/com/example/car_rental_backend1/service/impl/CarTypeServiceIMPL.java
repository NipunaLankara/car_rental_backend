package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.CarTypeRequestDTO;
import com.example.car_rental_backend1.dto.response.CarTypeResponseDTO;
import com.example.car_rental_backend1.entity.CarType;
import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.repo.CarTypeRepo;
import com.example.car_rental_backend1.service.CarTypeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarTypeServiceIMPL implements CarTypeService {
    @Autowired
    private CarTypeRepo carTypeRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String addNewCarType(CarTypeRequestDTO carTypeRequestDTO) {

        if (validateDetails(carTypeRequestDTO)) {

            if (!carTypeRepo.existsByTypeName(carTypeRequestDTO.getTypeName())) {

                CarType carType = modelMapper.map(carTypeRequestDTO, CarType.class);
                carTypeRepo.save(carType);

                return "Car Type " + carTypeRequestDTO.getTypeName() + " Added Successfully";
            } else {
                throw new AlreadyExistsException("Car Type " + carTypeRequestDTO.getTypeName() + " Already Exists");
            }
        } else {
            throw new IllegalArgumentException("Something Wrong");
        }
    }

    @Override
    public List<CarTypeResponseDTO> getAllCarTypes() {
        List<CarType> carTypeList = carTypeRepo.findAll();

        List<CarTypeResponseDTO> carTypeResponseDTOS = modelMapper.map(carTypeList, new TypeToken<List<CarTypeResponseDTO>>() {
        }.getType());

        return carTypeResponseDTOS;

    }

    @Override
    public String updateCarType(CarTypeRequestDTO carTypeRequestDTO) {
        int carTypeId = carTypeRequestDTO.getId();

        if (carTypeRepo.existsById(carTypeId)) {
          CarType carType =  carTypeRepo.getReferenceById(carTypeId);

          carType.setTypeName(carTypeRequestDTO.getTypeName());
          carType.setChargePerKm(carTypeRequestDTO.getChargePerKm());
          carType.setChargePerDay(carTypeRequestDTO.getChargePerDay());

          carTypeRepo.save(carType);
          return "Car Type : "+ carTypeRequestDTO.getTypeName() + " Updated Successfully";

        } else {
            throw new NotContentException("No Car Type For this Id " + carTypeId);
        }
    }

    @Override
    public String deleteCarTypeById(int id) {

        if (id>0) {
            if (carTypeRepo.existsById(id)) {
               carTypeRepo.deleteById(id);
               return "Car Type = " + id+ " is  Deleted Successfully";
            }else {
                throw new NotContentException("Car Type Not Found This Id :"+id);
            }
        } else {
            throw new IllegalArgumentException("Car Type Id is Empty");
        }
    }

    @Override
    public CarTypeResponseDTO getCarTypeById(int id) {

        if (id>0) {
            if (carTypeRepo.existsById(id)) {
                CarType carType = carTypeRepo.getReferenceById(id);
                CarTypeResponseDTO carTypeResponseDTO = modelMapper.map(carType, CarTypeResponseDTO.class);
                return carTypeResponseDTO;

            }else {
                throw new NotContentException("Car Type Not Found This Id :"+id);
            }
        }  else {
            throw new IllegalArgumentException("Car Type Id is Empty");
        }
    }

    private boolean validateDetails(CarTypeRequestDTO carTypeRequestDTO) {
        String typeName = carTypeRequestDTO.getTypeName();
        int chargePerDay = carTypeRequestDTO.getChargePerDay();
        int chargePerKm = carTypeRequestDTO.getChargePerKm();

        if (typeName.isEmpty() || String.valueOf(chargePerDay).isEmpty() || String.valueOf(chargePerKm).isEmpty()) {
            throw new IllegalArgumentException("Values Can not be Empty");
        }
        return true;

    }
}
