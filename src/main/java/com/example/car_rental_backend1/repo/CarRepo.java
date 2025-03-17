package com.example.car_rental_backend1.repo;

import com.example.car_rental_backend1.dto.response.BookingAndBillResponseDTO;
import com.example.car_rental_backend1.entity.CarNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository
@EnableJpaRepositories

public interface CarRepo extends JpaRepository<CarNew,String> {
    boolean existsByCarNumber(String carNumber);

    List<CarNew> findAllByStatusEquals(String status);

    CarNew getReferenceByCarNumber(String carNumber);

    void deleteByCarNumber(String carNumber);

    Page<CarNew> findAllByStatusEquals(String status, PageRequest pageRequest);

    Page<CarNew> findAllByCarType_IdAndStatusEquals(int typeId, String status, Pageable pageable);



/*
    @Query(value = "SELECT * FROM car_new WHERE type_id = :typeId AND status = :status LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<CarNew> findCarsByTypeAndStatusNative(
            @Param("typeId") int typeId,
            @Param("status") String status,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

*/

}
