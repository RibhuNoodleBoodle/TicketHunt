package com.project.bookingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT, reason = "seat not available")
public class SeatAlreadyBookedException extends Exception {
}
