package com.project.bookingservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "seats")
public class Seats {
    @Id
    private String seatNumber;
    private double price;
    private Date dateOfBooking;
    private String transactionId;
}