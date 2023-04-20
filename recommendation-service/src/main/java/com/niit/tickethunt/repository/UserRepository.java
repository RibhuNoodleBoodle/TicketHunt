package com.niit.tickethunt.repository;

import com.niit.tickethunt.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {
    @Query("MATCH (user:User), (event:Event) WHERE ID(user) = $user AND ID(event) = $event CREATE (user) - [:BOOKED] -> (event)")
    void addBooking(int event, int user);
}
