package com.example.car_rental_backend1.repo;

import com.example.car_rental_backend1.dto.queryinterfaces.BookingAndBillDetailsInterface;
import com.example.car_rental_backend1.dto.response.BookingAndBillResponseDTO;
import com.example.car_rental_backend1.entity.Bookings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BookingRepo extends JpaRepository<Bookings,Integer> {
    boolean existsByUser_UserId(int id);

    Page<Bookings> findAllByUser_UserId(int id, Pageable pageable);

    @Query(value = "SELECT b.booking_date AS bookingDate, b.start_date AS startDate, b.return_date AS returnDate, " +
            "b.start_millage AS startMillage, b.end_millage AS endMillage, " +
            "b.user_id AS userId, b.car_number AS carNumber, b.package_id AS carPackageId, " +
            "bi.package_price AS packagePrice, bi.addtional_charge_per_km AS addtionalChargePerKm, " +
            "bi.addtional_charge_per_day AS addtionalChargePerDay, bi.deposit AS deposit, " +
            "bi.discount AS discount, bi.total_price AS total, bi.prepayment AS prepayment, " +
            "bi.balance AS balance " +
            "FROM booking b " +
            "JOIN bill bi ON b.booking_id = bi.booking_id " +
            "WHERE b.booking_id = ?1", nativeQuery = true)
    Page<BookingAndBillDetailsInterface> getBookingDetailsAndBillDetailsByBookingId(int id, Pageable pageable);


}
