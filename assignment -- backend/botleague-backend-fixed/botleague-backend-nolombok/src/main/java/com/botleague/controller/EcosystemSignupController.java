package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.dto.EcosystemSignupDto;
import com.botleague.service.EcosystemSignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ecosystem")
public class EcosystemSignupController {

    private final EcosystemSignupService ecosystemSignupService;
    public EcosystemSignupController(EcosystemSignupService ecosystemSignupService) {
        this.ecosystemSignupService = ecosystemSignupService;
    }


    /** Public – anyone can sign up (Judge / Volunteer / Community Member) */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<EcosystemSignupDto.Response>> signup(
            @Valid @RequestBody EcosystemSignupDto.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Signup submitted successfully",
                ecosystemSignupService.signup(request)));
    }

    /** Admin only – list signups by role */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<EcosystemSignupDto.Response>>> getByRole(
            @RequestParam String role) {
        return ResponseEntity.ok(ApiResponse.success(ecosystemSignupService.getAllByRole(role)));
    }
}
