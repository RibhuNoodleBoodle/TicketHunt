package com.project.bookingservice.controller;

import com.project.bookingservice.domain.Booking;
import com.project.bookingservice.domain.Seats;
import com.project.bookingservice.exceptions.*;
import com.project.bookingservice.repository.BookingRepository;
import com.project.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/bookings")
//@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    private BookingRepository bookingRepository;

    private ResponseEntity responseEntity;



    @PostMapping("/addBooking")
    public ResponseEntity<?> insertBooking(@RequestBody Booking booking) throws EventAlreadyExistException {

        try {
            booking.setSeatList(new ArrayList<>());
            return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.OK);
        } catch (EventAlreadyExistException e) {
            throw new EventAlreadyExistException();
        }
    }

    @PostMapping("/book/{eventId}/{email}")
    public ResponseEntity<?> bookSeats(@PathVariable String eventId, @PathVariable String email, @RequestBody Seats seats) throws SeatAlreadyBookedException {

        try {
            return new ResponseEntity<>(bookingService.bookSeats(eventId, email, seats), HttpStatus.OK);

        } catch (Exception e) {
            throw new SeatAlreadyBookedException();
        }

    }


    @PostMapping("/{email}/{message}")
    public void sendConfirmationEmail(@PathVariable String email,@PathVariable String message) {
          bookingService.sendEmail(email, message);
    }



    @DeleteMapping("/cancel/{eventId}/{email}/{seats}")
    public ResponseEntity<?> cancelSeats(@PathVariable String eventId, @PathVariable String email, @PathVariable String seats) throws SeatNotFoundException {

        try {
            bookingService.cancelTickets(eventId, email, seats);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        } catch (SeatNotFoundException e) {
            throw new SeatNotFoundException();
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(bookingService.getAllBookings(), HttpStatus.OK);
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }


    @GetMapping("/{email}")
    public ResponseEntity<?> GetBookingByEmailId(@PathVariable String email) throws EventNotFoundException {

        try {
            return new ResponseEntity<>(bookingService.findByEmail(email), HttpStatus.OK);

        } catch (Exception e) {
            throw new EventNotFoundException();
        }
    }

    @GetMapping("/view/{eventId}")
    public ResponseEntity<?> getBookingByEventId(@PathVariable String eventId) throws EventNotFoundException {

        try {
            return new ResponseEntity<>(bookingService.findByEventId(eventId), HttpStatus.OK);

        } catch (Exception e) {
            throw new EventNotFoundException();
        }

    }

    @GetMapping("/{eventId}/{email}")
    public ResponseEntity<?> getBookingByEventIdAndEmail(@PathVariable String eventId,@PathVariable String email) throws EventNotFoundException {
        try {
            return new ResponseEntity<>(bookingService.findByEventIdAndEmail(eventId, email), HttpStatus.OK);

        } catch (Exception e) {
            throw new EventNotFoundException();
        }

    }
}
