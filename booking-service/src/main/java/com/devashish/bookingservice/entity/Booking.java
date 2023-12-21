package com.devashish.bookingservice.entity;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.*;
import org.springframework.data.auditing.CurrentDateTimeProvider;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID", nullable = false, unique = true)
    private Long bookingID;

    private Date fromDate;

    private Date toDate;

    private String aadharNumber;

    private Integer numOfRooms;

    private String usersRooms;

    @Column(nullable = false)
    private Integer roomPrice;

    private Integer transactionID;

    private Date bookedOn;

//  Setting new bookings' transaction id to 0 since no payment has been done yet
    @PrePersist
    public void prePersist() {
        if (this.transactionID == null) {
            this.transactionID = 0;
        }
    }
}
