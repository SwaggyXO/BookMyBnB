package com.devashish.bookingservice.service;

import com.devashish.bookingservice.dto.BookingRequest;
import com.devashish.bookingservice.entity.Booking;
import com.devashish.bookingservice.repository.BookingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
    public Optional<Booking> getBooking (Long bookingID) {
        return bookingRepository.findById(bookingID);
    }

    public Booking saveBooking(BookingRequest bookingRequest)
    {
        String rooms = "";

        int roomsNeeded = bookingRequest.numberOfRooms;

        for (int i = 0; i < roomsNeeded; i++) {
            int randomNum = RandomGenerator.getDefault().nextInt(0, 100);
            String randomGenNum = String.valueOf(randomNum) + ",";

            rooms += randomGenNum;
        }

        int totalCost = (int) (roomsNeeded * 1000 * Duration.between(bookingRequest.toDate.toInstant(), bookingRequest.fromDate.toInstant()).toDays());

        Booking booking = getBookingObject(bookingRequest, rooms.substring(0, rooms.length() - 1), totalCost);

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking updateTransactionInfo (Integer transactionID, Long bookingID) {
        bookingRepository.updateTransactionID(bookingID, transactionID);

        return entityManager.find(Booking.class, bookingID);
    }

    public Booking getBookingObject(BookingRequest bookingRequest, String rooms, int totalCost)
    {
        Booking booking = new Booking();
        booking.setFromDate(bookingRequest.fromDate);
        booking.setToDate(bookingRequest.toDate);
        booking.setAadharNumber(bookingRequest.aadharNumber);
        booking.setNumOfRooms(bookingRequest.numberOfRooms);
        booking.setUsersRooms(rooms);
        booking.setRoomPrice(totalCost);
        booking.setBookedOn(new Date());

        return booking;
    }
}
