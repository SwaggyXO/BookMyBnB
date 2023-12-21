package com.devashish.bookingservice.service;

import com.devashish.bookingservice.dto.BookingRequest;
import com.devashish.bookingservice.dto.PaymentRequest;
import com.devashish.bookingservice.entity.Booking;
import com.devashish.bookingservice.repository.BookingRepository;
import com.devashish.bookingservice.utils.BookingExceptionHandler;
import com.devashish.bookingservice.utils.exception.InvalidBookingIDException;
import com.devashish.bookingservice.utils.exception.InvalidPaymentMethodException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final WebClient.Builder webClientBuilder;

    @PersistenceContext
    private EntityManager entityManager;
    
    public Booking getBooking (Long bookingID) {

        return bookingRepository.findById(bookingID).orElseThrow(() -> new InvalidBookingIDException("Invalid Booking ID!"));
    }

    public Booking saveBooking(BookingRequest bookingRequest)
    {
        String rooms = "";

        int roomsNeeded = bookingRequest.numberOfRooms;

//      Generating random numbers for room, makeshift as asked in the document
        for (int i = 0; i < roomsNeeded; i++) {
            int randomNum = RandomGenerator.getDefault().nextInt(0, 100);
            String randomGenNum = String.valueOf(randomNum) + ",";

            rooms += randomGenNum;
        }

//      Computing total cost per day keeping number of rooms in mind
        int totalCost = (int) (roomsNeeded * 1000 * Duration.between(bookingRequest.fromDate.toInstant(), bookingRequest.toDate.toInstant()).toDays());

        Booking booking = getBookingObject(bookingRequest, rooms.substring(0, rooms.length() - 1), totalCost);

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking updateTransactionInfo (PaymentRequest paymentRequest) {

        if (paymentRequest.bookingID == null || getBooking(paymentRequest.bookingID) == null)
        {
            throw new InvalidBookingIDException("Invalid booking id!");
        }

        if (paymentRequest.paymentMethod.equals("CARD") || paymentRequest.paymentMethod.equals("UPI"))
        {
//            Inter service communication: Updating transaction details
            Integer transactionID = webClientBuilder.build().post().uri("http://payment-service/api/payment/")
                    .body(BodyInserters.fromValue(paymentRequest))
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();

            bookingRepository.updateTransactionID(paymentRequest.bookingID, transactionID);

            return entityManager.find(Booking.class, paymentRequest.bookingID);
        }

        else throw new InvalidPaymentMethodException("Invalid payment method!");

    }

//    Custom method for creating a booking obejct to be saved in the database
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
