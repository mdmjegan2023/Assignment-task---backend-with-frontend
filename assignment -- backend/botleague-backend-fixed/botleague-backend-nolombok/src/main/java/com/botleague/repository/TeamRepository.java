package com.botleague.repository;

import com.botleague.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    List<Team> findByCategoryOrderByTotalScoreDesc(String category);
    List<Team> findByCaptainId(String captainId);
    List<Team> findByMemberIdsContaining(String userId);
    Optional<Team> findByName(String name);
    boolean existsByName(String name);
}
