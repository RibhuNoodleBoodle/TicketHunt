package com.project.MovieEventService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Document(collection = "images")
public class Event {
    @Id
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

}
