package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.CarPackageRequestDTO;
import com.example.car_rental_backend1.dto.response.CarPackageResponseDTO;
import com.example.car_rental_backend1.entity.CarPackage;
import com.example.car_rental_backend1.entity.CarType;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.repo.CarPackRepo;
import com.example.car_rental_backend1.repo.CarTypeRepo;
import com.example.car_rental_backend1.service.CarPackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarPackServiceIMPL implements CarPackService {
    @Autowired
    private CarPackRepo carPackRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CarTypeRepo  carTypeRepo;

    @Override
    public String addNewCarPackage(CarPackageRequestDTO carPackageRequestDTO) {

        if (carPackageRequestDTO.toString() != null) {

            CarPackage carPackage = modelMapper.map(carPackageRequestDTO,CarPackage.class);
            carPackRepo.save(carPackage);
            return "Car Package "+ carPackageRequestDTO.getPackageDuration()+" added Successfully";

        } else {
            throw new IllegalArgumentException("Invalid value");
        }


    }

    @Override
    public List<CarPackageResponseDTO> getAllCarPacakge() {
        List<CarPackage> carPackages = carPackRepo.findAll();

        if (!carPackages.isEmpty()) {
            List<CarPackageResponseDTO> carPackageResponseDTOList = new ArrayList<CarPackageResponseDTO>();

            for (CarPackage carPackage : carPackages) {
                CarPackageResponseDTO carPackageResponseDTO = new CarPackageResponseDTO(
                        carPackage.getId(),
                        carPackage.getPackageDuration(),
                        carPackage.getDistanceLimitKm(),
                        carPackage.getPrice(),
                        carPackage.getCarType().getId()
                );
                carPackageResponseDTOList.add(carPackageResponseDTO);
            }
            return carPackageResponseDTOList;
        } else {
            throw new NotContentException("No Car Packages");
        }

    }

    @Override
    public String updateCarPackage(CarPackageRequestDTO carPackageRequestDTO) {
        int carPackageId = carPackageRequestDTO.getId();
        CarType carType = carTypeRepo.findById(carPackageRequestDTO.getTypeId()).orElseThrow(() -> new RuntimeException("Car Type not found"));


        if (carPackRepo.existsById(carPackageId)) {
            CarPackage carPackage =  carPackRepo.getReferenceById(carPackageId);

            carPackage.setPackageDuration(carPackageRequestDTO.getPackageDuration());
            carPackage.setDistanceLimitKm(carPackageRequestDTO.getDistanceLimitKm());
            carPackage.setPrice(carPackageRequestDTO.getPrice());
            carPackage.setCarType(carType);

            carPackRepo.save(carPackage);
            return "Car Package : "+ carPackageRequestDTO.getId() + " Updated Successfully";

        } else {
            throw new NotContentException("No Car Package For this Id " + carPackageId);
        }
    }

    @Override
    public String deleteCarPackageById(int id) {
        if (id>0) {
            if (carPackRepo.existsById(id)) {
                carPackRepo.deleteById(id);
                return "Car Package = " + id+ " is  Deleted Successfully";
            }else {
                throw new NotContentException("Car Package Not Found This Id :"+id);
            }
        } else {
            throw new IllegalArgumentException("Car Package Id is Empty");
        }
    }
}
