package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bracket_matches")
public class BracketMatch {

    @Id
    private String id;
    private String eventId;
    private int round;
    private int matchNumber;
    private String participant1Id;
    private String participant1Name;
    private String participant2Id;
    private String participant2Name;
    private String winnerId;
    private String winnerName;
    private Integer score1;
    private Integer score2;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime completedAt;

    public BracketMatch() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, eventId, participant1Id, participant1Name,
                participant2Id, participant2Name, winnerId, winnerName, status;
        private int round, matchNumber;
        private Integer score1, score2;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime completedAt;

        public Builder id(String v)                  { id = v; return this; }
        public Builder eventId(String v)             { eventId = v; return this; }
        public Builder round(int v)                  { round = v; return this; }
        public Builder matchNumber(int v)            { matchNumber = v; return this; }
        public Builder participant1Id(String v)      { participant1Id = v; return this; }
        public Builder participant1Name(String v)    { participant1Name = v; return this; }
        public Builder participant2Id(String v)      { participant2Id = v; return this; }
        public Builder participant2Name(String v)    { participant2Name = v; return this; }
        public Builder winnerId(String v)            { winnerId = v; return this; }
        public Builder winnerName(String v)          { winnerName = v; return this; }
        public Builder score1(Integer v)             { score1 = v; return this; }
        public Builder score2(Integer v)             { score2 = v; return this; }
        public Builder status(String v)              { status = v; return this; }
        public Builder createdAt(LocalDateTime v)    { createdAt = v; return this; }
        public Builder completedAt(LocalDateTime v)  { completedAt = v; return this; }

        public BracketMatch build() {
            BracketMatch m = new BracketMatch();
            m.id = id; m.eventId = eventId; m.round = round;
            m.matchNumber = matchNumber; m.participant1Id = participant1Id;
            m.participant1Name = participant1Name; m.participant2Id = participant2Id;
            m.participant2Name = participant2Name; m.winnerId = winnerId;
            m.winnerName = winnerName; m.score1 = score1; m.score2 = score2;
            m.status = status; m.createdAt = createdAt; m.completedAt = completedAt;
            return m;
        }
    }

    public String getId()                          { return id; }
    public void setId(String id)                   { this.id = id; }
    public String getEventId()                     { return eventId; }
    public void setEventId(String eventId)         { this.eventId = eventId; }
    public int getRound()                          { return round; }
    public void setRound(int round)                { this.round = round; }
    public int getMatchNumber()                    { return matchNumber; }
    public void setMatchNumber(int matchNumber)    { this.matchNumber = matchNumber; }
    public String getParticipant1Id()              { return participant1Id; }
    public void setParticipant1Id(String v)        { this.participant1Id = v; }
    public String getParticipant1Name()            { return participant1Name; }
    public void setParticipant1Name(String v)      { this.participant1Name = v; }
    public String getParticipant2Id()              { return participant2Id; }
    public void setParticipant2Id(String v)        { this.participant2Id = v; }
    public String getParticipant2Name()            { return participant2Name; }
    public void setParticipant2Name(String v)      { this.participant2Name = v; }
    public String getWinnerId()                    { return winnerId; }
    public void setWinnerId(String winnerId)       { this.winnerId = winnerId; }
    public String getWinnerName()                  { return winnerName; }
    public void setWinnerName(String winnerName)   { this.winnerName = winnerName; }
    public Integer getScore1()                     { return score1; }
    public void setScore1(Integer score1)          { this.score1 = score1; }
    public Integer getScore2()                     { return score2; }
    public void setScore2(Integer score2)          { this.score2 = score2; }
    public String getStatus()                      { return status; }
    public void setStatus(String status)           { this.status = status; }
    public LocalDateTime getCreatedAt()            { return createdAt; }
    public void setCreatedAt(LocalDateTime t)      { this.createdAt = t; }
    public LocalDateTime getCompletedAt()          { return completedAt; }
    public void setCompletedAt(LocalDateTime t)    { this.completedAt = t; }
}
