package com.devashish.bookingservice.utils;

import com.devashish.bookingservice.dto.ErrorResponse;
import com.devashish.bookingservice.utils.exception.InvalidBookingIDException;
import com.devashish.bookingservice.utils.exception.InvalidPaymentMethodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidPaymentMethodException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse invalidPaymentMethod(InvalidPaymentMethodException paymentMethodException) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), paymentMethodException.getMessage());
    }

    @ExceptionHandler(InvalidBookingIDException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse invalidBookingID(InvalidBookingIDException bookingIDException) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), bookingIDException.getMessage());
    }
}
