package com.botleague.service;

import com.botleague.model.LeaderboardEntry;
import com.botleague.repository.LeaderboardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }


    public Page<LeaderboardEntry> getLeaderboard(String entityType, String category, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        if (category != null && !category.isBlank()) {
            return leaderboardRepository.findByEntityTypeAndCategoryOrderByRankAsc(entityType, category, pageable);
        }
        return leaderboardRepository.findByEntityTypeOrderByRankAsc(entityType, pageable);
    }

    public List<LeaderboardEntry> getTop10(String entityType) {
        return leaderboardRepository.findTop10ByEntityTypeOrderByRankAsc(entityType);
    }
}
