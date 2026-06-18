package com.botleague.service;

import com.botleague.dto.RegistrationDto;
import com.botleague.exception.BadRequestException;
import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.Event;
import com.botleague.model.Registration;
import com.botleague.repository.EventRepository;
import com.botleague.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    public RegistrationService(RegistrationRepository registrationRepository, EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }


    public RegistrationDto.Response registerForEvent(RegistrationDto.RegisterForEvent request, String userId) {
        Event event = eventRepository.findById(request.getEventId())
            .orElseThrow(() -> new ResourceNotFoundException("Event", request.getEventId()));

        if ("PAST".equals(event.getStatus())) {
            throw new BadRequestException("Cannot register for a past event");
        }

        if ("USER".equals(request.getRegistrationType())) {
            if (registrationRepository.existsByEventIdAndUserId(request.getEventId(), userId)) {
                throw new BadRequestException("You are already registered for this event");
            }
            Registration reg = Registration.builder()
                .eventId(request.getEventId())
                .registrationType("USER")
                .userId(userId)
                .status("CONFIRMED")
                .paymentStatus("FREE")
                .build();

            event.getRegisteredUserIds().add(userId);
            eventRepository.save(event);
            return toResponse(registrationRepository.save(reg), event.getTitle());

        } else if ("TEAM".equals(request.getRegistrationType())) {
            if (request.getTeamId() == null) {
                throw new BadRequestException("Team ID is required for team registration");
            }
            if (registrationRepository.existsByEventIdAndTeamId(request.getEventId(), request.getTeamId())) {
                throw new BadRequestException("Your team is already registered for this event");
            }
            Registration reg = Registration.builder()
                .eventId(request.getEventId())
                .registrationType("TEAM")
                .userId(userId)
                .teamId(request.getTeamId())
                .status("CONFIRMED")
                .paymentStatus("FREE")
                .build();

            event.getRegisteredTeamIds().add(request.getTeamId());
            eventRepository.save(event);
            return toResponse(registrationRepository.save(reg), event.getTitle());
        }
        throw new BadRequestException("Invalid registration type");
    }

    public List<RegistrationDto.Response> getMyRegistrations(String userId) {
        return registrationRepository.findByUserId(userId).stream()
            .map(reg -> {
                String title = eventRepository.findById(reg.getEventId())
                    .map(Event::getTitle).orElse("Unknown Event");
                return toResponse(reg, title);
            }).collect(Collectors.toList());
    }

    public void cancelRegistration(String registrationId, String userId) {
        Registration reg = registrationRepository.findById(registrationId)
            .orElseThrow(() -> new ResourceNotFoundException("Registration", registrationId));
        if (!reg.getUserId().equals(userId)) {
            throw new BadRequestException("Cannot cancel another user's registration");
        }
        reg.setStatus("CANCELLED");
        reg.setUpdatedAt(LocalDateTime.now());
        registrationRepository.save(reg);
    }

    private RegistrationDto.Response toResponse(Registration reg, String eventTitle) {
        RegistrationDto.Response r = new RegistrationDto.Response();
        r.setId(reg.getId());
        r.setEventId(reg.getEventId());
        r.setEventTitle(eventTitle);
        r.setRegistrationType(reg.getRegistrationType());
        r.setUserId(reg.getUserId());
        r.setTeamId(reg.getTeamId());
        r.setStatus(reg.getStatus());
        r.setRegisteredAt(reg.getRegisteredAt().toString());
        return r;
    }
}
