package com.devashish.bookingservice.utils.exception;

public class InvalidBookingIDException extends RuntimeException{
    public InvalidBookingIDException(String message) {
        super(message);
    }
}
