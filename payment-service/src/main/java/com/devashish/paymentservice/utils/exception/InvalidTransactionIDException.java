package com.devashish.paymentservice.utils.exception;

public class InvalidTransactionIDException extends RuntimeException{
    public InvalidTransactionIDException(String message)
    {
        super(message);
    }
}
