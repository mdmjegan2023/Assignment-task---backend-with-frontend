package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.dto.EventDto;
import com.botleague.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    // ── Public endpoints ─────────────────────────────────────────────────────

    @GetMapping("/api/events/public/all")
    public ResponseEntity<ApiResponse<List<EventDto.Response>>> getAllPublic() {
        return ResponseEntity.ok(ApiResponse.success(eventService.getAllEvents()));
    }

    @GetMapping("/api/events/public/live")
    public ResponseEntity<ApiResponse<List<EventDto.Response>>> getLive() {
        return ResponseEntity.ok(ApiResponse.success(eventService.getLiveEvents()));
    }

    @GetMapping("/api/events/public/upcoming")
    public ResponseEntity<ApiResponse<List<EventDto.Response>>> getUpcoming() {
        return ResponseEntity.ok(ApiResponse.success(eventService.getUpcomingEvents()));
    }

    @GetMapping("/api/events/public/past")
    public ResponseEntity<ApiResponse<List<EventDto.Response>>> getPast() {
        return ResponseEntity.ok(ApiResponse.success(eventService.getPastEvents()));
    }

    @GetMapping("/api/events/public/{id}")
    public ResponseEntity<ApiResponse<EventDto.Response>> getById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(eventService.getEventById(id)));
    }

    // ── Admin endpoints ───────────────────────────────────────────────────────

    @PostMapping("/api/admin/events")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EventDto.Response>> create(
            @Valid @RequestBody EventDto.CreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Event created", eventService.createEvent(request)));
    }

    @PutMapping("/api/admin/events/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EventDto.Response>> update(
            @PathVariable String id,
            @RequestBody EventDto.UpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Event updated", eventService.updateEvent(id, request)));
    }

    @DeleteMapping("/api/admin/events/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok(ApiResponse.success("Event deleted", null));
    }
}
