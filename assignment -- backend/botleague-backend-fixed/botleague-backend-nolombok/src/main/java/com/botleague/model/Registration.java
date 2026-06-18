package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "registrations")
public class Registration {

    @Id
    private String id;
    private String eventId;
    private String registrationType;
    private String userId;
    private String teamId;
    private String status;
    private LocalDateTime registeredAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private String paymentStatus;
    private String paymentReference;

    public Registration() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, eventId, registrationType, userId, teamId,
                status, paymentStatus, paymentReference;
        private LocalDateTime registeredAt = LocalDateTime.now();
        private LocalDateTime updatedAt;

        public Builder id(String v)                    { id = v; return this; }
        public Builder eventId(String v)               { eventId = v; return this; }
        public Builder registrationType(String v)      { registrationType = v; return this; }
        public Builder userId(String v)                { userId = v; return this; }
        public Builder teamId(String v)                { teamId = v; return this; }
        public Builder status(String v)                { status = v; return this; }
        public Builder registeredAt(LocalDateTime v)   { registeredAt = v; return this; }
        public Builder updatedAt(LocalDateTime v)      { updatedAt = v; return this; }
        public Builder paymentStatus(String v)         { paymentStatus = v; return this; }
        public Builder paymentReference(String v)      { paymentReference = v; return this; }

        public Registration build() {
            Registration r = new Registration();
            r.id = id; r.eventId = eventId;
            r.registrationType = registrationType; r.userId = userId;
            r.teamId = teamId; r.status = status;
            r.registeredAt = registeredAt; r.updatedAt = updatedAt;
            r.paymentStatus = paymentStatus; r.paymentReference = paymentReference;
            return r;
        }
    }

    public String getId()                              { return id; }
    public void setId(String id)                       { this.id = id; }
    public String getEventId()                         { return eventId; }
    public void setEventId(String eventId)             { this.eventId = eventId; }
    public String getRegistrationType()                { return registrationType; }
    public void setRegistrationType(String v)          { this.registrationType = v; }
    public String getUserId()                          { return userId; }
    public void setUserId(String userId)               { this.userId = userId; }
    public String getTeamId()                          { return teamId; }
    public void setTeamId(String teamId)               { this.teamId = teamId; }
    public String getStatus()                          { return status; }
    public void setStatus(String status)               { this.status = status; }
    public LocalDateTime getRegisteredAt()             { return registeredAt; }
    public void setRegisteredAt(LocalDateTime t)       { this.registeredAt = t; }
    public LocalDateTime getUpdatedAt()                { return updatedAt; }
    public void setUpdatedAt(LocalDateTime t)          { this.updatedAt = t; }
    public String getPaymentStatus()                   { return paymentStatus; }
    public void setPaymentStatus(String v)             { this.paymentStatus = v; }
    public String getPaymentReference()                { return paymentReference; }
    public void setPaymentReference(String v)          { this.paymentReference = v; }
}
