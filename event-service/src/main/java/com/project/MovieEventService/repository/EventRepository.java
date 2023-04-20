package com.project.MovieEventService.repository;

import com.project.MovieEventService.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByEmail(String email);

    //    Event findByEventType(String eventType);
    List<Event> findAllByEventType(String eventType);

    Event findByEventId(String eventId);


}
