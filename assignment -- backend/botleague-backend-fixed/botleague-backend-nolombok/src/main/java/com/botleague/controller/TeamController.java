package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.dto.TeamDto;
import com.botleague.security.UserDetailsImpl;
import com.botleague.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<TeamDto.Response>> create(
            @Valid @RequestBody TeamDto.CreateRequest request,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Team created", teamService.createTeam(request, user.getId())));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TeamDto.Response>>> getAll(
            @RequestParam(required = false) String category) {
        List<TeamDto.Response> teams = category != null
            ? teamService.getTeamsByCategory(category)
            : teamService.getAllTeams();
        return ResponseEntity.ok(ApiResponse.success(teams));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<TeamDto.Response>>> getMyTeams(
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(ApiResponse.success(teamService.getMyTeams(user.getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamDto.Response>> getById(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success(teamService.getTeamById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamDto.Response>> update(
            @PathVariable String id,
            @RequestBody TeamDto.UpdateRequest request,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(ApiResponse.success("Team updated",
            teamService.updateTeam(id, request, user.getId())));
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<ApiResponse<TeamDto.Response>> addMember(
            @PathVariable String id,
            @Valid @RequestBody TeamDto.AddMemberRequest request,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(ApiResponse.success("Member added",
            teamService.addMember(id, request.getUserId(), user.getId())));
    }

    @DeleteMapping("/{id}/members/{memberId}")
    public ResponseEntity<ApiResponse<TeamDto.Response>> removeMember(
            @PathVariable String id,
            @PathVariable String memberId,
            @AuthenticationPrincipal UserDetailsImpl user) {
        return ResponseEntity.ok(ApiResponse.success("Member removed",
            teamService.removeMember(id, memberId, user.getId())));
    }
}
