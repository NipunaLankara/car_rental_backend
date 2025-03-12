package com.example.car_rental_backend1.util.mappers;

import com.example.car_rental_backend1.dto.response.BookingResponseDTO;
import com.example.car_rental_backend1.entity.Bookings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//@Mapper(componentModel = "spring")
//public interface BookingMapper {
//
//    @Mappings({
//            @Mapping(source = "id", target = "bookingId"),
//            @Mapping(source = "user.id", target = "userId"),
//            @Mapping(source = "carPackage.id", target = "carPackageId"),
////            @Mapping(target = "carNumber", expression = "java(bookings.getCarNumber().getCarNumber())") // Directly extract the String
//    })
//    List<BookingResponseDTO> bookingListToDTOList(List<Bookings> bookingsList);
//}
//
//

