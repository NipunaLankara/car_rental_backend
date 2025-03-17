package com.example.car_rental_backend1.repo;

import com.example.car_rental_backend1.entity.Bookings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BookingRepo extends JpaRepository<Bookings,Integer> {
    boolean existsByUser_UserId(int id);

    Page<Bookings> findAllByUser_UserId(int id, Pageable pageable);

}
