package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.model.Sponsor;
import com.botleague.service.SponsorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorController {

    private final SponsorService sponsorService;
    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }


    /** Public – show sponsors on landing page */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Sponsor>>> getSponsors() {
        return ResponseEntity.ok(ApiResponse.success(sponsorService.getActiveSponsors()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Sponsor>> create(@RequestBody Sponsor sponsor) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Sponsor added", sponsorService.createSponsor(sponsor)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        sponsorService.deleteSponsor(id);
        return ResponseEntity.ok(ApiResponse.success("Sponsor removed", null));
    }
}
