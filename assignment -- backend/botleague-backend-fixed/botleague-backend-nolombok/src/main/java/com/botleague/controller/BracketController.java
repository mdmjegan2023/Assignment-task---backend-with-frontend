package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.model.BracketMatch;
import com.botleague.service.BracketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brackets")
public class BracketController {

    private final BracketService bracketService;

    public BracketController(BracketService bracketService) {
        this.bracketService = bracketService;
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<ApiResponse<List<BracketMatch>>> getBracket(@PathVariable String eventId) {
        return ResponseEntity.ok(ApiResponse.success(bracketService.getBracketForEvent(eventId)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BracketMatch>> createMatch(@RequestBody BracketMatch match) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Match created", bracketService.createMatch(match)));
    }

    @PatchMapping("/{matchId}/result")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BracketMatch>> updateResult(
            @PathVariable String matchId,
            @RequestBody MatchResultRequest body) {
        return ResponseEntity.ok(ApiResponse.success("Result recorded",
            bracketService.updateMatchResult(matchId, body.getWinnerId(),
                body.getScore1(), body.getScore2())));
    }

    static class MatchResultRequest {
        private String winnerId;
        private int score1, score2;

        public String getWinnerId()          { return winnerId; }
        public void setWinnerId(String v)    { this.winnerId = v; }
        public int getScore1()               { return score1; }
        public void setScore1(int v)         { this.score1 = v; }
        public int getScore2()               { return score2; }
        public void setScore2(int v)         { this.score2 = v; }
    }
}
