package com.botleague.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EventDto {

    public static class CreateRequest {
        @NotBlank private String title;
        private String description;
        @NotBlank private String city;
        @NotBlank private String location;
        @NotNull  private LocalDate eventDate;
        @NotBlank private String discipline;
        @NotBlank private String category;
        private int capacity;
        private String bannerImageUrl, streamUrl, episode;

        public String getTitle()               { return title; }
        public void setTitle(String v)         { this.title = v; }
        public String getDescription()         { return description; }
        public void setDescription(String v)   { this.description = v; }
        public String getCity()                { return city; }
        public void setCity(String v)          { this.city = v; }
        public String getLocation()            { return location; }
        public void setLocation(String v)      { this.location = v; }
        public LocalDate getEventDate()        { return eventDate; }
        public void setEventDate(LocalDate v)  { this.eventDate = v; }
        public String getDiscipline()          { return discipline; }
        public void setDiscipline(String v)    { this.discipline = v; }
        public String getCategory()            { return category; }
        public void setCategory(String v)      { this.category = v; }
        public int getCapacity()               { return capacity; }
        public void setCapacity(int v)         { this.capacity = v; }
        public String getBannerImageUrl()      { return bannerImageUrl; }
        public void setBannerImageUrl(String v){ this.bannerImageUrl = v; }
        public String getStreamUrl()           { return streamUrl; }
        public void setStreamUrl(String v)     { this.streamUrl = v; }
        public String getEpisode()             { return episode; }
        public void setEpisode(String v)       { this.episode = v; }
    }

    public static class UpdateRequest {
        private String title, description, city, location, status,
                discipline, category, bannerImageUrl, streamUrl, episode;
        private LocalDate eventDate;
        private Integer capacity;

        public String getTitle()               { return title; }
        public void setTitle(String v)         { this.title = v; }
        public String getDescription()         { return description; }
        public void setDescription(String v)   { this.description = v; }
        public String getCity()                { return city; }
        public void setCity(String v)          { this.city = v; }
        public String getLocation()            { return location; }
        public void setLocation(String v)      { this.location = v; }
        public LocalDate getEventDate()        { return eventDate; }
        public void setEventDate(LocalDate v)  { this.eventDate = v; }
        public String getStatus()              { return status; }
        public void setStatus(String v)        { this.status = v; }
        public String getDiscipline()          { return discipline; }
        public void setDiscipline(String v)    { this.discipline = v; }
        public String getCategory()            { return category; }
        public void setCategory(String v)      { this.category = v; }
        public Integer getCapacity()           { return capacity; }
        public void setCapacity(Integer v)     { this.capacity = v; }
        public String getBannerImageUrl()      { return bannerImageUrl; }
        public void setBannerImageUrl(String v){ this.bannerImageUrl = v; }
        public String getStreamUrl()           { return streamUrl; }
        public void setStreamUrl(String v)     { this.streamUrl = v; }
        public String getEpisode()             { return episode; }
        public void setEpisode(String v)       { this.episode = v; }
    }

    public static class Response {
        private String id, title, description, city, location, status,
                discipline, category, bannerImageUrl, streamUrl, episode;
        private LocalDate eventDate;
        private int capacity, registeredCount;

        public String getId()                  { return id; }
        public void setId(String v)            { this.id = v; }
        public String getTitle()               { return title; }
        public void setTitle(String v)         { this.title = v; }
        public String getDescription()         { return description; }
        public void setDescription(String v)   { this.description = v; }
        public String getCity()                { return city; }
        public void setCity(String v)          { this.city = v; }
        public String getLocation()            { return location; }
        public void setLocation(String v)      { this.location = v; }
        public LocalDate getEventDate()        { return eventDate; }
        public void setEventDate(LocalDate v)  { this.eventDate = v; }
        public String getStatus()              { return status; }
        public void setStatus(String v)        { this.status = v; }
        public String getDiscipline()          { return discipline; }
        public void setDiscipline(String v)    { this.discipline = v; }
        public String getCategory()            { return category; }
        public void setCategory(String v)      { this.category = v; }
        public int getCapacity()               { return capacity; }
        public void setCapacity(int v)         { this.capacity = v; }
        public int getRegisteredCount()        { return registeredCount; }
        public void setRegisteredCount(int v)  { this.registeredCount = v; }
        public String getBannerImageUrl()      { return bannerImageUrl; }
        public void setBannerImageUrl(String v){ this.bannerImageUrl = v; }
        public String getStreamUrl()           { return streamUrl; }
        public void setStreamUrl(String v)     { this.streamUrl = v; }
        public String getEpisode()             { return episode; }
        public void setEpisode(String v)       { this.episode = v; }
    }
}
