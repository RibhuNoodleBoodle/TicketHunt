package com.niit.tickethunt.controller;

import com.niit.tickethunt.domain.Event;
import com.niit.tickethunt.domain.User;
import com.niit.tickethunt.exception.EventNotFoundException;
import com.niit.tickethunt.service.EventService;
import com.niit.tickethunt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v4/")
public class GlobalController {
    private final EventService eventService;
    private final UserService userService;


    @Autowired
    public GlobalController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    /* Event CRUD related APIs */
    @GetMapping("events")
    public ResponseEntity<?> getAllEvents() {
        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @PostMapping("event/save")
    public ResponseEntity<?> saveEvent(@RequestBody Event event) {
        try {
            return new ResponseEntity<>(eventService.save(event), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage());
            return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("event/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) throws EventNotFoundException {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }


    /*  User CURD related APIs */

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("user/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("user/booked/{id}")
    public ResponseEntity<?> addEvent(@PathVariable String id) {
        int userId;
        if (Integer.parseInt(String.valueOf(id).split("")[0]) == 0) {
            userId = 0;
        }
        userId = Integer.parseInt(String.valueOf(id).split("")[0]);
        int eventId = Integer.parseInt(String.valueOf(id).split("")[1]);
        return new ResponseEntity<>(userService.addBooking(eventId, userId), HttpStatus.OK);
    }

    /*
     * Booking CRUD related APIs
     * There is no requirement of Booking for Neo4j
     *
     */


}
