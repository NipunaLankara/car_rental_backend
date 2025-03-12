package com.example.car_rental_backend1.service.impl;

import com.example.car_rental_backend1.dto.paginate.PaginateBookingResponseDTO;
import com.example.car_rental_backend1.dto.request.BookingRequestDTO;
import com.example.car_rental_backend1.dto.response.BookingResponseDTO;
import com.example.car_rental_backend1.entity.*;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.repo.*;
import com.example.car_rental_backend1.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
//    @Autowired
//    private BookingMapper bookingMapper;

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

    @Override
    public PaginateBookingResponseDTO getAllBookings(int page) {
        Page<Bookings> bookings = bookingRepo.findAll(PageRequest.of(page,10));

        if (bookings.hasContent()) {
            long count = bookingRepo.count();

//           List<BookingResponseDTO> bookingResponseDTOS = bookingMapper.bookingListToDTOList(bookings.getContent());

            List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<BookingResponseDTO>();
            for (Bookings bookings1 :bookings.getContent()) {
                BookingResponseDTO bookingResponseDTO = new BookingResponseDTO(
                        bookings1.getId(),
                        bookings1.getBookingDate(),
                        bookings1.getStartDate(),
                        bookings1.getReturnDate(),
                        bookings1.getStartMillage(),
                        bookings1.getEndMillage(),
                        bookings1.getUser().getUserId(),
                        bookings1.getCarNumber().getCarNumber(),
                        bookings1.getCarPackage().getId()
                );
                bookingResponseDTOList.add(bookingResponseDTO);
            }
           return new PaginateBookingResponseDTO(bookingResponseDTOList,count);


        } else {
            throw new NotContentException("Booking List Empty");
        }
    }
}
