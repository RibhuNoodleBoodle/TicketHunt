package com.project.bookingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "event already exists")
public class EventAlreadyExistException extends Exception {
}
