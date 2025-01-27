package com.example.car_rental_backend1.repo;

import com.example.car_rental_backend1.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories

public interface CarRepo extends JpaRepository<Car,Integer> {
    boolean existsByCarNumber(String carNumber);

    List<Car> findAllByStatusEquals(String status);

    Car getReferenceByCarNumber(String carNumber);


    void deleteByCarNumber(String carNumber);
}
