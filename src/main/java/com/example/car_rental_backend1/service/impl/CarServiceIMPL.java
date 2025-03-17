package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.paginate.PaginateCarResponseDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.dto.response.CarResponseDTO;

import com.example.car_rental_backend1.entity.CarNew;
import com.example.car_rental_backend1.entity.CarType;
import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.repo.CarRepo;
import com.example.car_rental_backend1.service.CarService;
import com.example.car_rental_backend1.util.mappers.CarMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private CarMapper carMapper;


    @Override
    public String addNewCar(CarRequestDTO carRequestDTO) {

        if (validateCarDetails(carRequestDTO)) {

            if (!carRepo.existsByCarNumber(carRequestDTO.getCarNumber())) {
                CarNew car = modelMapper.map(carRequestDTO, CarNew.class);
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
        List<CarNew> carList = carRepo.findAll();

        List<CarResponseDTO> carResponseDTOList = new ArrayList<CarResponseDTO>();

        for (CarNew car : carList) {
            CarResponseDTO carResponseDTO = new CarResponseDTO(
                    car.getCarNumber(),
                    car.getModel(),
                    car.getStatus(),
                    car.getCarType().getId(),
                    car.getCarType().getTypeName()
            );
            carResponseDTOList.add(carResponseDTO);
        }

        return carResponseDTOList;

    }

    @Override
    public List<CarResponseDTO> getAllCarsByStatus(String status) {

        if (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Booked")
                || status.equalsIgnoreCase("Repair")) {

            List<CarNew> carList = carRepo.findAllByStatusEquals(status);

            if (!carList.isEmpty()) {
                List<CarResponseDTO> carResponseDTOList = new ArrayList<>();

                for (CarNew car : carList) {
                    CarResponseDTO carResponseDTO = new CarResponseDTO(
                            car.getCarNumber(),
                            car.getModel(),
                            car.getStatus(),
                            car.getCarType().getId(),
                            car.getCarType().getTypeName()
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
    public PaginateCarResponseDTO getAllCarsWithPaginate(int page, int size) {
        Page<CarNew> carPage = carRepo.findAll(PageRequest.of(page, size));

        if (carPage.hasContent()) {
            long count = carRepo.count();

//            List<CarResponseDTO> carResponseDTOList = new ArrayList<>();
//
//            for (CarNew car : carPage.getContent()) {  // Using for-each
//                CarResponseDTO carResponseDTO = new CarResponseDTO(
//                        car.getCarNumber(),
//                        car.getModel(),
//                        car.getStatus(),
//                        car.getCarType().getId()
//                );
//                carResponseDTOList.add(carResponseDTO);
//            }
//            return new PaginateCarResponseDTO(carResponseDTOList,count);

            List<CarResponseDTO> carResponseDTOs = carMapper.carListToDTOList(carPage.getContent());
            return new PaginateCarResponseDTO(carResponseDTOs, count);

        } else {
            throw new NotContentException("Car List Empty");
        }
    }

    @Override
    public PaginateCarResponseDTO getCarByStatusWithPaginate(String status, int page) {
        int size = 10;
        Page<CarNew> carNewPage = carRepo.findAllByStatusEquals(status, PageRequest.of(page, size));

        if (carNewPage.hasContent()) {
            long count = carRepo.count();
            List<CarResponseDTO> carResponseDTOs = carMapper.carListToDTOList(carNewPage.getContent());
            return new PaginateCarResponseDTO(carResponseDTOs, count);

        } else {
            throw new NotContentException("Car List Empty");
        }
    }

    @Override
    public CarResponseDTO getCarByCaNumber(String carNumber) {

        if (carRepo.existsByCarNumber(carNumber)) {

            CarNew carNew = carRepo.getReferenceByCarNumber(carNumber);

            CarResponseDTO carResponseDTO = new CarResponseDTO(
                    carNew.getCarNumber(),
                    carNew.getModel(),
                    carNew.getStatus()
            );
            return carResponseDTO;

        } else {
            throw new NotContentException("No Car Details for car number = " + carNumber);
        }

    }

    @Override
    public PaginateCarResponseDTO getCarByCarTypeAndStatusWithPaginate(int typeId, String status, int page) {
        Page<CarNew> carNewPage =  carRepo.findAllByCarType_IdAndStatusEquals(typeId,status,PageRequest.of(page, 10));

        if (carNewPage.hasContent()) {
            long count = carRepo.count();
            List<CarResponseDTO> carResponseDTOs = carMapper.carListToDTOList(carNewPage.getContent());
            return new PaginateCarResponseDTO(carResponseDTOs, count);

        } else {
            throw new NotContentException("Car List Empty");
        }
    }


    @Override
    public String updateCar(CarRequestDTO carRequestDTO) {
        String carNumber = carRequestDTO.getCarNumber();

        System.out.println(carNumber);

        if (carRepo.existsByCarNumber(carNumber)) {

            CarNew updateCar = carRepo.getReferenceByCarNumber(carNumber);
            updateCar.setCarNumber(carRequestDTO.getCarNumber());
            updateCar.setModel(carRequestDTO.getModel());
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
        String status = carRequestDTO.getStatus();

        String exceptionError = null;

        if (carNumber == null || carNumber.isEmpty()) {
            exceptionError = "Car Number cannot be empty.";
        } else if (model == null || model.isEmpty()) {
            exceptionError = "Model cannot be empty.";
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
