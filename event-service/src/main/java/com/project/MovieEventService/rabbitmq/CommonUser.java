package com.project.MovieEventService.rabbitmq;

import lombok.Data;

@Data
public class CommonUser {


    private String eventId;
    private String email;
    private String eventName;
    private String organizerName;
    private String date;
    private String time;
    private String venue;
    private byte[] image;
    private int totalSeats;
    private String eventType;
    private String description;
    private int price;
    private String rating;

    public CommonUser(String eventId, String email, String eventName, String organizerName, String date, String time, String venue, byte[] image, int totalSeats, String eventType, String description, int price, String rating) {
        this.eventId = eventId;
        this.email = email;
        this.eventName = eventName;
        this.organizerName = organizerName;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.image = image;
        this.totalSeats = totalSeats;
        this.eventType = eventType;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }
}
