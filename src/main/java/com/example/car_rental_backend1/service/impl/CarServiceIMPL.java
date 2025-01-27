package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.response.CarResponseDTO;
import com.example.car_rental_backend1.entity.Car;
import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.repo.CarRepo;
import com.example.car_rental_backend1.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceIMPL implements CarService {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addNewCar(CarRequestDTO carRequestDTO) {

        if (validateCarDetails(carRequestDTO)) {

            if (!carRepo.existsByCarNumber(carRequestDTO.getCarNumber())) {
                Car car = modelMapper.map(carRequestDTO, Car.class);
                carRepo.save(car);
                return "Car Number " + carRequestDTO.getCarNumber() + " Added Successfully";
            } else {
                throw new AlreadyExistsException("Car Number " + carRequestDTO.getCarNumber() + " Already Exists");
            }
        } else {
            throw new IllegalArgumentException("Something Wrong");
        }
    }

    @Override
    public List<CarResponseDTO> getAllCars() {
        List<Car> carList = carRepo.findAll();

        List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

        for (Car car : carList) {
            CarResponseDTO carResponseDTO = new CarResponseDTO(
                    car.getCarNumber(),
                    car.getModel(),
                    car.getType(),
                    car.getStatus()
            );

            carResponseDTOList.add(carResponseDTO);
        }
        return carResponseDTOList;

    }

    @Override
    public List<CarResponseDTO> getAllCarsByStatus(String status) {

        if (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Booked")
                || status.equalsIgnoreCase("Repair")) {
            List<Car> carList = carRepo.findAllByStatusEquals(status);

            if (!carList.isEmpty()) {
                List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

                for (Car car : carList) {
                    CarResponseDTO carResponseDTO = new CarResponseDTO(
                            car.getCarNumber(),
                            car.getModel(),
                            car.getType(),
                            car.getStatus()
                    );
                    carResponseDTOList.add(carResponseDTO);
                }


//            List<CarResponseDTO> carResponseDTOList  = modelMapper.map(carList, new TypeToken<List<CarResponseDTO>>() {
//            }.getType());

                return carResponseDTOList;


            } else {
                throw new NotContentException("Car List Empty");
            }

        } else {
            throw new IllegalArgumentException("Status must be one of the following: Available, Booked, Repair");
        }


    }

    @Override
    public String updateCar(CarRequestDTO carRequestDTO) {
        String carNumber = carRequestDTO.getCarNumber();

        System.out.println(carNumber);

        if (carRepo.existsByCarNumber(carNumber)) {

            Car updateCar = carRepo.getReferenceByCarNumber(carNumber);
            updateCar.setCarNumber(carRequestDTO.getCarNumber());
            updateCar.setModel(carRequestDTO.getModel());
            updateCar.setType(carRequestDTO.getType());
            updateCar.setStatus(carRequestDTO.getStatus());

            carRepo.save(updateCar);

            return "Car Number " + carNumber + " Update Successfully";


        } else {
            throw new NotContentException("No Car Details for car number = " + carNumber);
        }
    }

    @Transactional
    @Override
    public String deleteCarByCarNumber(String carNumber) {

        if (carRepo.existsByCarNumber(carNumber)) {
            carRepo.deleteByCarNumber(carNumber);
            return "Car Number = " + carNumber+ " is Car Deleted Successfully";

        } else {
            throw new NotContentException("No Car Details Found for Car Number = "+carNumber);
        }
    }


    public boolean validateCarDetails(CarRequestDTO carRequestDTO) {
        String carNumber = carRequestDTO.getCarNumber();
        String model = carRequestDTO.getModel();
        String type = carRequestDTO.getType();
        String status = carRequestDTO.getStatus();

        String exceptionError = null;

        if (carNumber == null || carNumber.isEmpty()) {
            exceptionError = "Car Number cannot be empty.";
        } else if (model == null || model.isEmpty()) {
            exceptionError = "Model cannot be empty.";
        } else if (type == null || type.isEmpty()) {
            exceptionError = "Type cannot be empty.";
        } else if (!status.equalsIgnoreCase("Available") &&
                !status.equalsIgnoreCase("Booked") &&
                !status.equalsIgnoreCase("Repair")) {
            exceptionError = "Status must be one of the following: Available, Booked, Repair.";
        }

        if (exceptionError != null) {
            throw new IllegalArgumentException(exceptionError);
        }
        return true;
    }




}
