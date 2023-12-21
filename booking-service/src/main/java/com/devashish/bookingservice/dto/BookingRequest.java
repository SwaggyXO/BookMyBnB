package com.devashish.bookingservice.dto;

import java.time.ZonedDateTime;
import java.util.Date;

public class BookingRequest {
    public Date fromDate;

    public Date toDate;

    public String aadharNumber;

    public Integer numberOfRooms;
}
