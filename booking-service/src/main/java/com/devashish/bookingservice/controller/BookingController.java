package com.devashish.bookingservice.controller;

import com.devashish.bookingservice.dto.BookingRequest;
import com.devashish.bookingservice.dto.PaymentRequest;
import com.devashish.bookingservice.entity.Booking;
import com.devashish.bookingservice.service.BookingService;
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
    private final WebClient.Builder webClientBuilder;

    @GetMapping("/")
    public ResponseEntity<String> baseRequest() {
        return ResponseEntity.ok("Booking service - up");
    }

    @PostMapping("/")
    public ResponseEntity<Booking> saveBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.saveBooking(bookingRequest);

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Booking> makePayment(@RequestBody PaymentRequest paymentRequest)
    {
        Integer transactionID = webClientBuilder.build().post().uri("http://localhost:8001/api/payment/")
                .body(BodyInserters.fromValue(paymentRequest))
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

        Booking booking = bookingService.updateTransactionInfo(transactionID, paymentRequest.bookingID);

        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

}
