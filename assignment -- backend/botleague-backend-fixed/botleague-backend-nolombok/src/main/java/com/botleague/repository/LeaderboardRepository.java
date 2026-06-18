package com.botleague.repository;

import com.botleague.model.LeaderboardEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaderboardRepository extends MongoRepository<LeaderboardEntry, String> {
    Page<LeaderboardEntry> findByEntityTypeOrderByRankAsc(String entityType, Pageable pageable);
    Page<LeaderboardEntry> findByEntityTypeAndCategoryOrderByRankAsc(String entityType, String category, Pageable pageable);
    Optional<LeaderboardEntry> findByEntityId(String entityId);
    List<LeaderboardEntry> findTop10ByEntityTypeOrderByRankAsc(String entityType);
}
