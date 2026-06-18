package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.model.LeaderboardEntry;
import com.botleague.service.LeaderboardService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }


    /**
     * GET /api/leaderboard?entityType=USER&category=Robo+Minds&page=0&size=20
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<LeaderboardEntry>>> getLeaderboard(
            @RequestParam(defaultValue = "USER") String entityType,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(ApiResponse.success(
            leaderboardService.getLeaderboard(entityType, category, page, size)));
    }

    /**
     * GET /api/leaderboard/top10?entityType=USER
     */
    @GetMapping("/top10")
    public ResponseEntity<ApiResponse<List<LeaderboardEntry>>> getTop10(
            @RequestParam(defaultValue = "USER") String entityType) {
        return ResponseEntity.ok(ApiResponse.success(leaderboardService.getTop10(entityType)));
    }
}
