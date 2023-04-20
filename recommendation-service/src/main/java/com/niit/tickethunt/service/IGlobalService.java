package com.niit.tickethunt.service;

//import com.niit.tickethunt.domain.Event;

import com.niit.tickethunt.exception.EventNotFoundException;
import com.niit.tickethunt.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IGlobalService<T> {

    List<T> getAll();

    T save(T t);

    Optional<T> findById(int id) throws UserNotFoundException, EventNotFoundException;

    T update(T t);
}
