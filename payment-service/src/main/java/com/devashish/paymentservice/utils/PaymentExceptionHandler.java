package com.devashish.paymentservice.utils;

import com.devashish.paymentservice.dto.ErrorResponse;
import com.devashish.paymentservice.utils.exception.InvalidTransactionIDException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PaymentExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidTransactionIDException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse invalidTransactionID(InvalidTransactionIDException transactionIDException) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), transactionIDException.getMessage());
    }
}
