package com.project.MovieEventService.service;

import com.project.MovieEventService.domain.Event;
//import com.project.MovieEventService.domain.Movie;
import com.project.MovieEventService.exception.EventAlreadyFoundException;
import com.project.MovieEventService.exception.EventNotFoundException;
import com.project.MovieEventService.rabbitmq.CommonUser;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface EventService {

    Event registerEvent(Event event, MultipartFile file) throws EventAlreadyFoundException, IOException;

    Event addEvent1(CommonUser commonUser, MultipartFile file) throws IOException;
    List<Event> viewAllEvents();

    List<Event> getAllEventOfUser(String email) throws EventNotFoundException;

    Event getEventById(String eventId) throws EventNotFoundException;

    boolean deleteEvent(String eventId) throws EventNotFoundException;

    Event addEvent(Event event) throws EventAlreadyFoundException;

    Event updateEventDetails(String eventId, Event event);

    List<Event> findByEventType(String eventType);

    void saveImage(Event event);


//    String uploadImage(MultipartFile file)throws IOException;
}
