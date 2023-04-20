package com.niit.tickethunt.configuration;


import com.niit.tickethunt.domain.Event;
import com.niit.tickethunt.domain.User;
import com.niit.tickethunt.service.EventService;
import com.niit.tickethunt.service.UserService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final UserService userService;
    private EventService eventService;

    @Autowired
    public Consumer(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @RabbitListener(queuesToDeclare = @Queue("user_neo_queue"))
    public void getUserData(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());
        user.setRole(userDTO.getRole());
        user.setInterest(userDTO.getInterest());
        user.setPhone(userDTO.getPhone());
        userService.save(user);
    }

    @RabbitListener(queuesToDeclare = @Queue("booking_neo"))
    public void getEventData(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getEventName());
        event.setOrganizer(eventDTO.getOrganizerName());
        event.setDate(eventDTO.getDate());
        event.setTime(eventDTO.getTime());
        event.setVenue(eventDTO.getVenue());
        event.setSeats(String.valueOf(eventDTO.getTotalSeats()));
        event.setPrice(eventDTO.getPrice());
        event.setDescription(eventDTO.getDescription());
        event.setRating(eventDTO.getRating());
        eventService.save(event);
    }
}
