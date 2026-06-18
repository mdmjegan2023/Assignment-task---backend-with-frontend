package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "leaderboard")
public class LeaderboardEntry {

    @Id
    private String id;
    private String entityType;
    private String entityId;
    private String entityName;
    private String category;
    private int totalScore;
    private int rank;
    private int eventsParticipated;
    private int wins;
    private LocalDateTime lastUpdated = LocalDateTime.now();
    private String avatarColor;

    public LeaderboardEntry() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, entityType, entityId, entityName, category, avatarColor;
        private int totalScore, rank, eventsParticipated, wins;
        private LocalDateTime lastUpdated = LocalDateTime.now();

        public Builder id(String v)                    { id = v; return this; }
        public Builder entityType(String v)            { entityType = v; return this; }
        public Builder entityId(String v)              { entityId = v; return this; }
        public Builder entityName(String v)            { entityName = v; return this; }
        public Builder category(String v)              { category = v; return this; }
        public Builder totalScore(int v)               { totalScore = v; return this; }
        public Builder rank(int v)                     { rank = v; return this; }
        public Builder eventsParticipated(int v)       { eventsParticipated = v; return this; }
        public Builder wins(int v)                     { wins = v; return this; }
        public Builder lastUpdated(LocalDateTime v)    { lastUpdated = v; return this; }
        public Builder avatarColor(String v)           { avatarColor = v; return this; }

        public LeaderboardEntry build() {
            LeaderboardEntry e = new LeaderboardEntry();
            e.id = id; e.entityType = entityType; e.entityId = entityId;
            e.entityName = entityName; e.category = category;
            e.totalScore = totalScore; e.rank = rank;
            e.eventsParticipated = eventsParticipated; e.wins = wins;
            e.lastUpdated = lastUpdated; e.avatarColor = avatarColor;
            return e;
        }
    }

    public String getId()                              { return id; }
    public void setId(String id)                       { this.id = id; }
    public String getEntityType()                      { return entityType; }
    public void setEntityType(String entityType)       { this.entityType = entityType; }
    public String getEntityId()                        { return entityId; }
    public void setEntityId(String entityId)           { this.entityId = entityId; }
    public String getEntityName()                      { return entityName; }
    public void setEntityName(String entityName)       { this.entityName = entityName; }
    public String getCategory()                        { return category; }
    public void setCategory(String category)           { this.category = category; }
    public int getTotalScore()                         { return totalScore; }
    public void setTotalScore(int totalScore)          { this.totalScore = totalScore; }
    public int getRank()                               { return rank; }
    public void setRank(int rank)                      { this.rank = rank; }
    public int getEventsParticipated()                 { return eventsParticipated; }
    public void setEventsParticipated(int v)           { this.eventsParticipated = v; }
    public int getWins()                               { return wins; }
    public void setWins(int wins)                      { this.wins = wins; }
    public LocalDateTime getLastUpdated()              { return lastUpdated; }
    public void setLastUpdated(LocalDateTime t)        { this.lastUpdated = t; }
    public String getAvatarColor()                     { return avatarColor; }
    public void setAvatarColor(String avatarColor)     { this.avatarColor = avatarColor; }
}
