package com.project.MovieEventService.service;

import com.project.MovieEventService.domain.Event;
//import com.project.MovieEventService.domain.Movie;
import com.project.MovieEventService.exception.EventAlreadyFoundException;
import com.project.MovieEventService.exception.EventNotFoundException;
import com.project.MovieEventService.proxy.BookingProxy;
import com.project.MovieEventService.rabbitmq.*;
import com.project.MovieEventService.repository.EventRepository;
//import com.project.MovieEventService.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Producer producer;

    @Autowired
    private ProducerMapping producerMapping;


    private BookingProxy bookingProxy;

    public EventServiceImpl(EventRepository eventRepository, BookingProxy bookingProxy, ProducerMapping producerMapping) {
        this.eventRepository = eventRepository;
        this.bookingProxy = bookingProxy;
        this.producerMapping = producerMapping;
    }

    @Override
    public Event registerEvent(Event event, MultipartFile file) throws EventAlreadyFoundException, IOException {
        log.debug("Entered the register profile()");
        Event registeredEvent = new Event();
        Optional<Event> registerEvent = eventRepository.findById(event.getEventName());
        if (registerEvent.isPresent()) {
            log.error("Event is already present");
            throw new EventAlreadyFoundException();
        } else {
            event.setImage(file.getBytes());
            registeredEvent = eventRepository.save(event);
            bookingProxy.saveBooking(event);
            return registeredEvent;
        }
    }

    @Override
    public Event addEvent1(CommonUser commonUser, MultipartFile file) throws IOException {

        List<SeatsDTO> seatsDTOList = new ArrayList<>();
        BookingDTO bookingDTO = new BookingDTO(commonUser.getEventId(), commonUser.getEmail(), commonUser.getEventName(), commonUser.getDate(), commonUser.getTime(), commonUser.getVenue(), seatsDTOList, commonUser.getTotalSeats());
        BookingDTO bookingDTO2 = new BookingDTO(commonUser.getEmail(), commonUser.getEventName(), commonUser.getDate(), commonUser.getTime(), commonUser.getVenue(),
                commonUser.getTotalSeats(), commonUser.getPrice(), commonUser.getDescription(), commonUser.getRating(), commonUser.getEventType());

        if (eventRepository.findByEventId(commonUser.getEventId()) == null) {
            producer.sendDtoToQueue(bookingDTO);
        }
        producerMapping.sendDtoToQueue(bookingDTO2);
        Event event = new Event(commonUser.getEventId(), commonUser.getEmail(), commonUser.getEventName(), commonUser.getOrganizerName(), commonUser.getDate(), commonUser.getTime(), commonUser.getVenue(),
                file.getBytes(), commonUser.getTotalSeats(), commonUser.getEventType(), commonUser.getDescription(), commonUser.getPrice(), commonUser.getRating());

        return eventRepository.insert(event);
    }

    @Override
    public List<Event> viewAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventOfUser(String email) throws EventNotFoundException {
        List<Event> event = eventRepository.findByEmail(email);
        if (event == null) {
            throw new EventNotFoundException();
        }
        return eventRepository.findByEmail(email);

    }

    @Override
    public Event getEventById(String eventId) throws EventNotFoundException {
        if (eventRepository.findByEventId(eventId) == null) {
            throw new EventNotFoundException();
        }
        return eventRepository.findByEventId(eventId);
    }

    @Override
    public boolean deleteEvent(String eventId) throws EventNotFoundException {
//        boolean result = false;
        if (eventRepository.findById(eventId).isEmpty()) {
            throw new EventNotFoundException();
        } else {
            eventRepository.deleteById(eventId);
            return true;
        }
    }

    @Override
    public Event addEvent(Event event) throws EventAlreadyFoundException {
        if (eventRepository.findById(event.getEventId()).isPresent()) {
            throw new EventAlreadyFoundException();
        }
        return eventRepository.save(event);
    }

    @Override
    public Event updateEventDetails(String eventId, Event event) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return null;
        }
        Event existingEvent = optionalEvent.get();
        if (event.getEmail() != null) {
            existingEvent.setEmail(event.getEmail());
        }
        if (event.getEventName() != null) {
            existingEvent.setEventName(event.getEventName());
        }
        if (event.getOrganizerName() != null) {
            existingEvent.setOrganizerName(event.getOrganizerName());
        }
        if (event.getDate() != null) {
            existingEvent.setDate(event.getDate());
        }
        if (event.getTime() != null) {
            existingEvent.setTime(event.getTime());
        }
        if (event.getVenue() != null) {
            existingEvent.setVenue(event.getVenue());
        }
        if (event.getTotalSeats() != -1) {
            existingEvent.setTotalSeats(event.getTotalSeats());
        }
        return eventRepository.save(existingEvent);
    }

    @Override
    public List<Event> findByEventType(String eventType) {
        if (eventType.equals("movie")) {
            return eventRepository.findAllByEventType("movie");
        }
        return eventRepository.findAllByEventType("event");
    }

    public void saveImage(Event image) {
        eventRepository.save(image);
    }


//    public String uploadImage(MultipartFile file) throws IOException {
//
//        Event imageData = eventRepository.save(Event.builder()
//                .image(ImageUtils.compressImage(file.getBytes())).build());
//        if (imageData != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
//        }
//        return null;
//    }


}
