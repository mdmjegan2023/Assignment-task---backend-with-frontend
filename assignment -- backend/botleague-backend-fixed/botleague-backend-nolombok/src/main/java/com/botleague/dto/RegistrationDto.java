package com.botleague.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistrationDto {

    public static class RegisterForEvent {
        @NotBlank private String eventId;
        @NotBlank private String registrationType;
        private String teamId;

        public String getEventId()                 { return eventId; }
        public void setEventId(String v)           { this.eventId = v; }
        public String getRegistrationType()        { return registrationType; }
        public void setRegistrationType(String v)  { this.registrationType = v; }
        public String getTeamId()                  { return teamId; }
        public void setTeamId(String v)            { this.teamId = v; }
    }

    public static class Response {
        private String id, eventId, eventTitle, registrationType,
                userId, teamId, status, registeredAt;

        public String getId()                      { return id; }
        public void setId(String v)                { this.id = v; }
        public String getEventId()                 { return eventId; }
        public void setEventId(String v)           { this.eventId = v; }
        public String getEventTitle()              { return eventTitle; }
        public void setEventTitle(String v)        { this.eventTitle = v; }
        public String getRegistrationType()        { return registrationType; }
        public void setRegistrationType(String v)  { this.registrationType = v; }
        public String getUserId()                  { return userId; }
        public void setUserId(String v)            { this.userId = v; }
        public String getTeamId()                  { return teamId; }
        public void setTeamId(String v)            { this.teamId = v; }
        public String getStatus()                  { return status; }
        public void setStatus(String v)            { this.status = v; }
        public String getRegisteredAt()            { return registeredAt; }
        public void setRegisteredAt(String v)      { this.registeredAt = v; }
    }
}
