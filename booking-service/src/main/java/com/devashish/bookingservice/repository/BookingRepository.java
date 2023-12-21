package com.devashish.bookingservice.repository;

import com.devashish.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Modifying
    @Query("UPDATE Booking b SET b.transactionID = :transactionID WHERE b.bookingID = :bookingID")
    void updateTransactionID(@Param("bookingID") Long bookingID, @Param("transactionID") Integer transactionID);
}
