package com.botleague.service;

import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.BracketMatch;
import com.botleague.repository.BracketMatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BracketService {

    private final BracketMatchRepository bracketMatchRepository;
    public BracketService(BracketMatchRepository bracketMatchRepository) {
        this.bracketMatchRepository = bracketMatchRepository;
    }


    public List<BracketMatch> getBracketForEvent(String eventId) {
        return bracketMatchRepository.findByEventIdOrderByRoundAscMatchNumberAsc(eventId);
    }

    public BracketMatch createMatch(BracketMatch match) {
        return bracketMatchRepository.save(match);
    }

    public BracketMatch updateMatchResult(String matchId, String winnerId, int score1, int score2) {
        BracketMatch match = bracketMatchRepository.findById(matchId)
            .orElseThrow(() -> new ResourceNotFoundException("Match", matchId));
        match.setWinnerId(winnerId);
        match.setScore1(score1);
        match.setScore2(score2);
        match.setStatus("COMPLETED");
        match.setCompletedAt(LocalDateTime.now());
        String winnerName = winnerId.equals(match.getParticipant1Id())
            ? match.getParticipant1Name() : match.getParticipant2Name();
        match.setWinnerName(winnerName);
        return bracketMatchRepository.save(match);
    }
}
