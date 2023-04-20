package com.niit.tickethunt.service;

import com.niit.tickethunt.domain.Event;
import com.niit.tickethunt.exception.EventNotFoundException;
import com.niit.tickethunt.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements IGlobalService<Event> {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> findById(int id) throws EventNotFoundException {
        if (eventRepository.findById((long) id).isEmpty())
            throw new EventNotFoundException("User not found");
        return eventRepository.findById((long) id);
    }

    @Override
    public Event update(Event event) {
        Event eventUpdate = new Event();
        if (!event.getName().isEmpty()) {
            eventUpdate.setName(event.getName());
        }
        if (!event.getDate().isEmpty()) {
            eventUpdate.setDate(event.getDate());
        }
        if (event.getPrice() < 0) {
            eventUpdate.setPrice(event.getPrice());
        }
        return null;
    }
}
