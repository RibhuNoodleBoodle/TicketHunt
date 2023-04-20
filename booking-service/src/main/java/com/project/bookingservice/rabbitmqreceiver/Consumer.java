package com.project.bookingservice.rabbitmqreceiver;

import com.project.bookingservice.domain.Booking;
import com.project.bookingservice.exceptions.EventAlreadyExistException;
import com.project.bookingservice.service.BookingService;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private BookingService bookingService;

    @RabbitListener(queuesToDeclare = @Queue("booking_queue"))
    public void getDtoFromQueueAndAddToDb(BookingDTO bookingDTO) throws EventAlreadyExistException {

        Booking booking = new Booking();
        booking.setEventId(bookingDTO.getEventId());
        booking.setTotalSeats(bookingDTO.getTotalSeats());
        booking.setEmail(bookingDTO.getEmail());
        booking.setEventName(bookingDTO.getEventName());
        booking.setSeatList(bookingDTO.getSeatList());
        booking.setDate(bookingDTO.getDate());
        booking.setTime(bookingDTO.getTime());
        booking.setVenue(bookingDTO.getVenue());


        Booking book = bookingService.addBooking(booking);
        System.out.println(book);


    }


}
