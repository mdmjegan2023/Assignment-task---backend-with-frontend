package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "events")
public class Event {

    @Id
    private String id;
    private String title;
    private String description;
    private String city;
    private String location;
    private LocalDate eventDate;
    private String status;
    private String discipline;
    private String category;
    private int capacity;
    private List<String> registeredTeamIds = new ArrayList<>();
    private List<String> registeredUserIds = new ArrayList<>();
    private String bannerImageUrl;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private String streamUrl;
    private String episode;

    public Event() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, title, description, city, location, status,
                discipline, category, bannerImageUrl, streamUrl, episode;
        private LocalDate eventDate;
        private int capacity;
        private List<String> registeredTeamIds = new ArrayList<>();
        private List<String> registeredUserIds = new ArrayList<>();
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt;

        public Builder id(String v)                        { id = v; return this; }
        public Builder title(String v)                     { title = v; return this; }
        public Builder description(String v)               { description = v; return this; }
        public Builder city(String v)                      { city = v; return this; }
        public Builder location(String v)                  { location = v; return this; }
        public Builder eventDate(LocalDate v)              { eventDate = v; return this; }
        public Builder status(String v)                    { status = v; return this; }
        public Builder discipline(String v)                { discipline = v; return this; }
        public Builder category(String v)                  { category = v; return this; }
        public Builder capacity(int v)                     { capacity = v; return this; }
        public Builder registeredTeamIds(List<String> v)   { registeredTeamIds = v; return this; }
        public Builder registeredUserIds(List<String> v)   { registeredUserIds = v; return this; }
        public Builder bannerImageUrl(String v)            { bannerImageUrl = v; return this; }
        public Builder createdAt(LocalDateTime v)          { createdAt = v; return this; }
        public Builder updatedAt(LocalDateTime v)          { updatedAt = v; return this; }
        public Builder streamUrl(String v)                 { streamUrl = v; return this; }
        public Builder episode(String v)                   { episode = v; return this; }

        public Event build() {
            Event e = new Event();
            e.id = id; e.title = title; e.description = description;
            e.city = city; e.location = location; e.eventDate = eventDate;
            e.status = status; e.discipline = discipline; e.category = category;
            e.capacity = capacity; e.registeredTeamIds = registeredTeamIds;
            e.registeredUserIds = registeredUserIds;
            e.bannerImageUrl = bannerImageUrl; e.createdAt = createdAt;
            e.updatedAt = updatedAt; e.streamUrl = streamUrl; e.episode = episode;
            return e;
        }
    }

    public String getId()                               { return id; }
    public void setId(String id)                        { this.id = id; }
    public String getTitle()                            { return title; }
    public void setTitle(String title)                  { this.title = title; }
    public String getDescription()                      { return description; }
    public void setDescription(String description)      { this.description = description; }
    public String getCity()                             { return city; }
    public void setCity(String city)                    { this.city = city; }
    public String getLocation()                         { return location; }
    public void setLocation(String location)            { this.location = location; }
    public LocalDate getEventDate()                     { return eventDate; }
    public void setEventDate(LocalDate eventDate)       { this.eventDate = eventDate; }
    public String getStatus()                           { return status; }
    public void setStatus(String status)                { this.status = status; }
    public String getDiscipline()                       { return discipline; }
    public void setDiscipline(String discipline)        { this.discipline = discipline; }
    public String getCategory()                         { return category; }
    public void setCategory(String category)            { this.category = category; }
    public int getCapacity()                            { return capacity; }
    public void setCapacity(int capacity)               { this.capacity = capacity; }
    public List<String> getRegisteredTeamIds()          { return registeredTeamIds; }
    public void setRegisteredTeamIds(List<String> v)    { this.registeredTeamIds = v; }
    public List<String> getRegisteredUserIds()          { return registeredUserIds; }
    public void setRegisteredUserIds(List<String> v)    { this.registeredUserIds = v; }
    public String getBannerImageUrl()                   { return bannerImageUrl; }
    public void setBannerImageUrl(String v)             { this.bannerImageUrl = v; }
    public LocalDateTime getCreatedAt()                 { return createdAt; }
    public void setCreatedAt(LocalDateTime t)           { this.createdAt = t; }
    public LocalDateTime getUpdatedAt()                 { return updatedAt; }
    public void setUpdatedAt(LocalDateTime t)           { this.updatedAt = t; }
    public String getStreamUrl()                        { return streamUrl; }
    public void setStreamUrl(String streamUrl)          { this.streamUrl = streamUrl; }
    public String getEpisode()                          { return episode; }
    public void setEpisode(String episode)              { this.episode = episode; }
}
