package com.botleague.repository;

import com.botleague.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByStatus(String status);
    List<Event> findByStatusOrderByEventDateAsc(String status);
    List<Event> findByDiscipline(String discipline);
    List<Event> findByCategory(String category);
    List<Event> findByStatusAndCategory(String status, String category);
    List<Event> findByRegisteredUserIdsContaining(String userId);
    List<Event> findByRegisteredTeamIdsContaining(String teamId);
}
