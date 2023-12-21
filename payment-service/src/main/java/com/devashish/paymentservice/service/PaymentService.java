package com.devashish.paymentservice.service;

import com.devashish.paymentservice.dto.PaymentRequest;
import com.devashish.paymentservice.entity.Transaction;
import com.devashish.paymentservice.repository.PaymentRepository;
import com.devashish.paymentservice.utils.exception.InvalidTransactionIDException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Integer saveTransaction(PaymentRequest paymentRequest) {
        Transaction transaction = new Transaction();

        transaction.setPaymentMethod(paymentRequest.paymentMethod);
        transaction.setBookingID(paymentRequest.bookingID);

        return paymentRepository.save(transaction).getTransactionID();
    }

    public Transaction getTransaction(Integer transactionID) {
        return paymentRepository.findById(transactionID).orElseThrow(() -> new InvalidTransactionIDException("Invalid transaction id!"));
    }

}
