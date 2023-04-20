package com.niit.tickethunt.repository;

import com.niit.tickethunt.domain.Event;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends Neo4jRepository<Event, Long> {

}
