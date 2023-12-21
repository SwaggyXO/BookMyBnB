package com.devashish.bookingservice.controller;

import com.devashish.bookingservice.dto.BookingRequest;
import com.devashish.bookingservice.dto.PaymentRequest;
import com.devashish.bookingservice.entity.Booking;
import com.devashish.bookingservice.service.BookingService;
import com.devashish.bookingservice.utils.exception.InvalidPaymentMethodException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

//    Base request for booking service
    @GetMapping("/")
    public ResponseEntity<String> baseRequest() {
        return ResponseEntity.ok("Booking service - up");
    }

//    Endpoint for saving a users booking
    @PostMapping("/")
    public ResponseEntity<Booking> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.saveBooking(bookingRequest);

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

//  Updating transaction details for a valid booking with payment method as UPI / CARD
    @PostMapping("/transaction")
    public ResponseEntity<Booking> makePayment(@RequestBody PaymentRequest paymentRequest)
    {
        Booking booking = bookingService.updateTransactionInfo(paymentRequest);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

}
