package com.project.bookingservice.repository;

import com.project.bookingservice.domain.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByEventId(String evenId);

    List<Booking> findByEmail(String email);

    ////   Booking findBySeatListSeatNumber(String evenId, String seatNumber);
//
//    Booking findByEventIdAndSeatListSeatNumber(String evenId, String seatNumber);
//
    Booking findByEventIdAndEmail(String eventId, String email);


}
