package com.ticketless.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ticketless.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    Booking findByorderId(String orderId);

    @Query(value = "select b from Booking b where b.users.mobileNo=?1")
    List<Booking> getUserOrders(String username);

    @Query(value = "select b from Booking b where b.qrCode.qrId=?1")
    Optional<Booking> findByQRId(String bookingId);

}
