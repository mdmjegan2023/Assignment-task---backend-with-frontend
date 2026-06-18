package com.botleague.repository;

import com.botleague.model.BracketMatch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BracketMatchRepository extends MongoRepository<BracketMatch, String> {
    List<BracketMatch> findByEventIdOrderByRoundAscMatchNumberAsc(String eventId);
    List<BracketMatch> findByEventIdAndRound(String eventId, int round);
    List<BracketMatch> findByStatus(String status);
}
