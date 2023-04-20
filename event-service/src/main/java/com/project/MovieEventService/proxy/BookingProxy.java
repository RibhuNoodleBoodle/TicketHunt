package com.project.MovieEventService.proxy;

import com.project.MovieEventService.domain.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "booking-service", url = "http://booking-service:8081")
public interface BookingProxy {

    @PostMapping("/bookings/addBooking")
    ResponseEntity<?> saveBooking(@RequestBody Event booking);


}
