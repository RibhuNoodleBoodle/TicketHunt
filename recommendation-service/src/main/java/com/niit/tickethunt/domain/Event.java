package com.niit.tickethunt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String date;
    private String time;
    private String venue;
    private String organizer;
    private String seats;
    private double price;
    private String type;
    private String description;
    private String rating;
}
