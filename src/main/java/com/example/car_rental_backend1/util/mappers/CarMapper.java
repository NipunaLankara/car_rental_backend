package com.example.car_rental_backend1.util.mappers;

import com.example.car_rental_backend1.dto.response.CarResponseDTO;
import com.example.car_rental_backend1.entity.CarNew;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {


//    @Mappings({
//            @Mapping(source = "carType.id", target = "typeId")
//    })
//    List<CarResponseDTO> listPageToListDTO(Page<CarNew> carPage);


    // Mapping CarNew to CarResponseDTO
    @Mappings({
            @Mapping(source = "carType.id", target = "typeId")
    })
    CarResponseDTO carToCarDTO(CarNew carNew);

    // Convert List of CarNew to List of CarResponseDTO
    List<CarResponseDTO> carListToDTOList(List<CarNew> carList);

}
