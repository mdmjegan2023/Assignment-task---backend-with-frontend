package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.dto.RegistrationDto;
import com.botleague.security.UserDetailsImpl;
import com.botleague.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<RegistrationDto.Response>> register(
            @Valid @RequestBody RegistrationDto.RegisterForEvent request,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Registered successfully",
                registrationService.registerForEvent(request, user.getId())));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<RegistrationDto.Response>>> getMyRegistrations(
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(ApiResponse.success(
            registrationService.getMyRegistrations(user.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> cancel(
            @PathVariable String id,
            @AuthenticationPrincipal UserDetailsImpl user) {
        registrationService.cancelRegistration(id, user.getId());
        return ResponseEntity.ok(ApiResponse.success("Registration cancelled", null));
    }
}
