package com.botleague.repository;

import com.botleague.model.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {
    List<Registration> findByEventId(String eventId);
    List<Registration> findByUserId(String userId);
    List<Registration> findByTeamId(String teamId);
    Optional<Registration> findByEventIdAndUserId(String eventId, String userId);
    Optional<Registration> findByEventIdAndTeamId(String eventId, String teamId);
    boolean existsByEventIdAndUserId(String eventId, String userId);
    boolean existsByEventIdAndTeamId(String eventId, String teamId);
}
