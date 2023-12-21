package com.devashish.paymentservice.controller;

import com.devashish.paymentservice.dto.PaymentRequest;
import com.devashish.paymentservice.entity.Transaction;
import com.devashish.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

//    Base request for payment service
    @GetMapping("/")
    public ResponseEntity<String> baseRequest() {
        return ResponseEntity.ok("Payment service - up");
    }

//    Endpoint for saving an incoming transaction.
//    This endpoint also helps booking service for fetching updated transaction details
    @PostMapping("/")
    public Integer saveTransaction(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.saveTransaction(paymentRequest);
    }

//  Fetching details of an existing transaction
    @GetMapping("/{transactionID}")
    public Transaction getTransactionDetails (@PathVariable Integer transactionID) {
        return paymentService.getTransaction(transactionID);
    }
}
