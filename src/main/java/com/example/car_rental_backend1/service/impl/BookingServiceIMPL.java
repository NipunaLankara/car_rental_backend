package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.request.BookingRequestDTO;
import com.example.car_rental_backend1.dto.request.CarRequestDTO;
import com.example.car_rental_backend1.entity.*;
import com.example.car_rental_backend1.repo.*;
import com.example.car_rental_backend1.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceIMPL implements BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private CarPackRepo carPackRepo;
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public String addNewBooking(BookingRequestDTO bookingRequestDTO ) {

        if (bookingRequestDTO.toString() != null) {

            User user = userRepo.findById(bookingRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            CarNew car = carRepo.findById(bookingRequestDTO.getCarNumber()).orElseThrow(() -> new RuntimeException("Car not found"));
            CarPackage carPackage = carPackRepo.findById(bookingRequestDTO.getCarPackageId()).orElseThrow(() -> new RuntimeException("Package not found"));

            // Create Booking entity
            Bookings booking = new Bookings();
            booking.setBookingDate(bookingRequestDTO.getBookingDate());
            booking.setStartDate(bookingRequestDTO.getStartDate());
            booking.setReturnDate(bookingRequestDTO.getReturnDate());
            booking.setStartMillage(bookingRequestDTO.getStartMillage());
            booking.setEndMillage(bookingRequestDTO.getEndMillage());
            booking.setUser(user);
            booking.setCarNumber(car);
            booking.setCarPackage(carPackage);

            Bookings savedBooking = bookingRepo.save(booking);

            // Create Bill entity
            Bill bill = new Bill();
            bill.setPackagePrice(bookingRequestDTO.getPackagePrice());
            bill.setAddtionalChargePerKm(bookingRequestDTO.getAddtionalChargePerKm());
            bill.setAddtionalChargePerDay(bookingRequestDTO.getAddtionalChargePerDay());
            bill.setDiscount(bookingRequestDTO.getDiscount());
            bill.setTotal(bookingRequestDTO.getTotal());
            bill.setPrepayment(bookingRequestDTO.getPrepayment());
            bill.setBalance(bookingRequestDTO.getBalance());
            bill.setDeposit(bookingRequestDTO.getDeposit());
            bill.setBookingId(savedBooking);

            billRepo.save(bill);
            return "Booking Car = "+bookingRequestDTO.getCarNumber()+" Added Successfully";

        } else {
            throw new IllegalArgumentException("Invalid Values");
        }

    }
}
