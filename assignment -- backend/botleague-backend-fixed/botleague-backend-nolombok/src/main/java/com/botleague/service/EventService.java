package com.botleague.service;

import com.botleague.dto.EventDto;
import com.botleague.exception.BadRequestException;
import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.Event;
import com.botleague.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public EventDto.Response createEvent(EventDto.CreateRequest request) {
        Event event = Event.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .city(request.getCity())
            .location(request.getLocation())
            .eventDate(request.getEventDate())
            .status("UPCOMING")
            .discipline(request.getDiscipline())
            .category(request.getCategory())
            .capacity(request.getCapacity())
            .bannerImageUrl(request.getBannerImageUrl())
            .streamUrl(request.getStreamUrl())
            .episode(request.getEpisode())
            .build();
        return toResponse(eventRepository.save(event));
    }

    public List<EventDto.Response> getAllEvents() {
        return eventRepository.findAll().stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    public List<EventDto.Response> getEventsByStatus(String status) {
        return eventRepository.findByStatusOrderByEventDateAsc(status).stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    public List<EventDto.Response> getLiveEvents() {
        return getEventsByStatus("LIVE");
    }

    public List<EventDto.Response> getUpcomingEvents() {
        return getEventsByStatus("UPCOMING");
    }

    public List<EventDto.Response> getPastEvents() {
        return getEventsByStatus("PAST");
    }

    public EventDto.Response getEventById(String id) {
        return toResponse(findById(id));
    }

    public EventDto.Response updateEvent(String id, EventDto.UpdateRequest request) {
        Event event = findById(id);
        if (request.getTitle() != null) event.setTitle(request.getTitle());
        if (request.getDescription() != null) event.setDescription(request.getDescription());
        if (request.getCity() != null) event.setCity(request.getCity());
        if (request.getLocation() != null) event.setLocation(request.getLocation());
        if (request.getEventDate() != null) event.setEventDate(request.getEventDate());
        if (request.getStatus() != null) event.setStatus(request.getStatus());
        if (request.getDiscipline() != null) event.setDiscipline(request.getDiscipline());
        if (request.getCategory() != null) event.setCategory(request.getCategory());
        if (request.getCapacity() != null) event.setCapacity(request.getCapacity());
        if (request.getBannerImageUrl() != null) event.setBannerImageUrl(request.getBannerImageUrl());
        if (request.getStreamUrl() != null) event.setStreamUrl(request.getStreamUrl());
        if (request.getEpisode() != null) event.setEpisode(request.getEpisode());
        event.setUpdatedAt(LocalDateTime.now());
        return toResponse(eventRepository.save(event));
    }

    public void deleteEvent(String id) {
        findById(id);
        eventRepository.deleteById(id);
    }

    private Event findById(String id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Event", id));
    }

    private EventDto.Response toResponse(Event event) {
        EventDto.Response r = new EventDto.Response();
        r.setId(event.getId());
        r.setTitle(event.getTitle());
        r.setDescription(event.getDescription());
        r.setCity(event.getCity());
        r.setLocation(event.getLocation());
        r.setEventDate(event.getEventDate());
        r.setStatus(event.getStatus());
        r.setDiscipline(event.getDiscipline());
        r.setCategory(event.getCategory());
        r.setCapacity(event.getCapacity());
        r.setRegisteredCount(event.getRegisteredUserIds().size() + event.getRegisteredTeamIds().size());
        r.setBannerImageUrl(event.getBannerImageUrl());
        r.setStreamUrl(event.getStreamUrl());
        r.setEpisode(event.getEpisode());
        return r;
    }
}
