package com.example.car_rental_backend1.repo;

import com.example.car_rental_backend1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
