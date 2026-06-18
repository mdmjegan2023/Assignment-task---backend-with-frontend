package com.botleague.service;

import com.botleague.dto.TeamDto;
import com.botleague.exception.BadRequestException;
import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.Team;
import com.botleague.model.User;
import com.botleague.repository.TeamRepository;
import com.botleague.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }


    public TeamDto.Response createTeam(TeamDto.CreateRequest request, String captainId) {
        if (teamRepository.existsByName(request.getName())) {
            throw new BadRequestException("Team name already taken");
        }
        Team team = Team.builder()
            .name(request.getName())
            .captainId(captainId)
            .institution(request.getInstitution())
            .category(request.getCategory())
            .city(request.getCity())
            .state(request.getState())
            .logoUrl(request.getLogoUrl())
            .build();
        team.getMemberIds().add(captainId);
        return toResponse(teamRepository.save(team));
    }

    public TeamDto.Response getTeamById(String id) {
        return toResponse(findById(id));
    }

    public List<TeamDto.Response> getAllTeams() {
        return teamRepository.findAll().stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    public List<TeamDto.Response> getTeamsByCategory(String category) {
        return teamRepository.findByCategoryOrderByTotalScoreDesc(category).stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    public TeamDto.Response updateTeam(String id, TeamDto.UpdateRequest request, String requesterId) {
        Team team = findById(id);
        if (!team.getCaptainId().equals(requesterId)) {
            throw new BadRequestException("Only the captain can update team details");
        }
        if (request.getName() != null) team.setName(request.getName());
        if (request.getInstitution() != null) team.setInstitution(request.getInstitution());
        if (request.getCategory() != null) team.setCategory(request.getCategory());
        if (request.getCity() != null) team.setCity(request.getCity());
        if (request.getState() != null) team.setState(request.getState());
        if (request.getLogoUrl() != null) team.setLogoUrl(request.getLogoUrl());
        team.setUpdatedAt(LocalDateTime.now());
        return toResponse(teamRepository.save(team));
    }

    public TeamDto.Response addMember(String teamId, String newUserId, String requesterId) {
        Team team = findById(teamId);
        if (!team.getCaptainId().equals(requesterId)) {
            throw new BadRequestException("Only the captain can add members");
        }
        if (team.getMemberIds().contains(newUserId)) {
            throw new BadRequestException("User is already in the team");
        }
        // Verify user exists
        userRepository.findById(newUserId)
            .orElseThrow(() -> new ResourceNotFoundException("User", newUserId));
        team.getMemberIds().add(newUserId);
        team.setUpdatedAt(LocalDateTime.now());
        return toResponse(teamRepository.save(team));
    }

    public TeamDto.Response removeMember(String teamId, String memberId, String requesterId) {
        Team team = findById(teamId);
        if (!team.getCaptainId().equals(requesterId)) {
            throw new BadRequestException("Only the captain can remove members");
        }
        if (memberId.equals(team.getCaptainId())) {
            throw new BadRequestException("Captain cannot be removed; transfer captaincy first");
        }
        team.getMemberIds().remove(memberId);
        team.setUpdatedAt(LocalDateTime.now());
        return toResponse(teamRepository.save(team));
    }

    public List<TeamDto.Response> getMyTeams(String userId) {
        return teamRepository.findByMemberIdsContaining(userId).stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    private Team findById(String id) {
        return teamRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Team", id));
    }

    private TeamDto.Response toResponse(Team team) {
        TeamDto.Response r = new TeamDto.Response();
        r.setId(team.getId());
        r.setName(team.getName());
        r.setCaptainId(team.getCaptainId());
        r.setMemberIds(team.getMemberIds());
        r.setInstitution(team.getInstitution());
        r.setCategory(team.getCategory());
        r.setCity(team.getCity());
        r.setState(team.getState());
        r.setTotalScore(team.getTotalScore());
        r.setNationalRank(team.getNationalRank());
        r.setLogoUrl(team.getLogoUrl());
        r.setActive(team.isActive());

        // Populate captain name
        userRepository.findById(team.getCaptainId())
            .ifPresent(u -> r.setCaptainName(u.getFullName()));

        // Populate member names
        List<String> memberNames = team.getMemberIds().stream()
            .map(id -> userRepository.findById(id)
                .map(User::getFullName).orElse("Unknown"))
            .collect(Collectors.toList());
        r.setMemberNames(memberNames);

        return r;
    }
}
