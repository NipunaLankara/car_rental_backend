package com.example.car_rental_backend1.util.mappers;

import com.example.car_rental_backend1.dto.response.BookingResponseDTO;
import com.example.car_rental_backend1.entity.Bookings;
import com.example.car_rental_backend1.entity.CarNew;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

/*@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mappings({
            @Mapping(source = "id", target = "bookingId"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "carPackage.id", target = "carPackageId"),
            @Mapping(source = "carNumber", target = "carNumber", qualifiedByName = "mapCarNewToString") // Use helper method
    })
    List<BookingResponseDTO> bookingListToDTOList(List<Bookings> bookingsList);

    @Named("mapCarNewToString")
    default String mapCarNewToString(CarNew car) {
        return (car != null) ? car.getCarNumber() : null;
    }
}*/
