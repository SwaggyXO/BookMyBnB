package com.devashish.bookingservice.utils.exception;

public class InvalidPaymentMethodException extends RuntimeException{
    public InvalidPaymentMethodException(String message) {
        super(message);
    }
}
