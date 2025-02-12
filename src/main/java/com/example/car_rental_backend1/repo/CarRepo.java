package com.example.car_rental_backend1.repo;


import com.example.car_rental_backend1.entity.CarNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories

public interface CarRepo extends JpaRepository<CarNew,String> {
    boolean existsByCarNumber(String carNumber);

    List<CarNew> findAllByStatusEquals(String status);

    CarNew getReferenceByCarNumber(String carNumber);


    void deleteByCarNumber(String carNumber);
}
