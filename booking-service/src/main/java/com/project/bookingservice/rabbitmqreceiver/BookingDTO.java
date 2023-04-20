package com.project.bookingservice.rabbitmqreceiver;

import com.project.bookingservice.domain.Seats;
import lombok.Data;

import java.util.List;

@Data
public class BookingDTO {


    private String email;
    private String eventName;
    private String eventId;
    private String bookingId;
    private String venue;
    private List<Seats> seatList;
    private int totalSeats;
    private String date;
    private String time;
}