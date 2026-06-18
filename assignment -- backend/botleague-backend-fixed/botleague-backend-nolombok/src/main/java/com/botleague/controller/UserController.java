package com.botleague.controller;

import com.botleague.dto.ApiResponse;
import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.User;
import com.botleague.repository.UserRepository;
import com.botleague.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getMe(
            @AuthenticationPrincipal UserDetailsImpl principal) {
        User user = userRepository.findById(principal.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", principal.getId()));
        return ResponseEntity.ok(ApiResponse.success(toProfile(user)));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateMe(
            @RequestBody UpdateProfileRequest request,
            @AuthenticationPrincipal UserDetailsImpl principal) {
        User user = userRepository.findById(principal.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", principal.getId()));
        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getCity() != null) user.setCity(request.getCity());
        if (request.getState() != null) user.setState(request.getState());
        if (request.getInstitution() != null) user.setInstitution(request.getInstitution());
        if (request.getCategory() != null) user.setCategory(request.getCategory());
        if (request.getProfileImageUrl() != null) user.setProfileImageUrl(request.getProfileImageUrl());
        user.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(ApiResponse.success("Profile updated",
            toProfile(userRepository.save(user))));
    }

    @PatchMapping("/me/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal UserDetailsImpl principal) {
        User user = userRepository.findById(principal.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", principal.getId()));
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Current password is incorrect"));
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.success("Password changed", null));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserById(@PathVariable String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return ResponseEntity.ok(ApiResponse.success(toProfile(user)));
    }

    // ── Inner classes ─────────────────────────────────────────────────────────

    public static class UpdateProfileRequest {
        private String fullName, phone, city, state, institution, category, profileImageUrl;

        public String getFullName()                  { return fullName; }
        public void setFullName(String v)            { this.fullName = v; }
        public String getPhone()                     { return phone; }
        public void setPhone(String v)               { this.phone = v; }
        public String getCity()                      { return city; }
        public void setCity(String v)                { this.city = v; }
        public String getState()                     { return state; }
        public void setState(String v)               { this.state = v; }
        public String getInstitution()               { return institution; }
        public void setInstitution(String v)         { this.institution = v; }
        public String getCategory()                  { return category; }
        public void setCategory(String v)            { this.category = v; }
        public String getProfileImageUrl()           { return profileImageUrl; }
        public void setProfileImageUrl(String v)     { this.profileImageUrl = v; }
    }

    public static class ChangePasswordRequest {
        private String currentPassword, newPassword;

        public String getCurrentPassword()           { return currentPassword; }
        public void setCurrentPassword(String v)     { this.currentPassword = v; }
        public String getNewPassword()               { return newPassword; }
        public void setNewPassword(String v)         { this.newPassword = v; }
    }

    public static class UserProfileResponse {
        private String id, username, email, fullName, phone, city, state,
                institution, category, profileImageUrl;
        private int totalScore;
        private Integer nationalRank;
        private Set<String> roles;
        private LocalDateTime createdAt;

        public String getId()                        { return id; }
        public void setId(String v)                  { this.id = v; }
        public String getUsername()                  { return username; }
        public void setUsername(String v)            { this.username = v; }
        public String getEmail()                     { return email; }
        public void setEmail(String v)               { this.email = v; }
        public String getFullName()                  { return fullName; }
        public void setFullName(String v)            { this.fullName = v; }
        public String getPhone()                     { return phone; }
        public void setPhone(String v)               { this.phone = v; }
        public String getCity()                      { return city; }
        public void setCity(String v)                { this.city = v; }
        public String getState()                     { return state; }
        public void setState(String v)               { this.state = v; }
        public String getInstitution()               { return institution; }
        public void setInstitution(String v)         { this.institution = v; }
        public String getCategory()                  { return category; }
        public void setCategory(String v)            { this.category = v; }
        public int getTotalScore()                   { return totalScore; }
        public void setTotalScore(int v)             { this.totalScore = v; }
        public Integer getNationalRank()             { return nationalRank; }
        public void setNationalRank(Integer v)       { this.nationalRank = v; }
        public String getProfileImageUrl()           { return profileImageUrl; }
        public void setProfileImageUrl(String v)     { this.profileImageUrl = v; }
        public Set<String> getRoles()                { return roles; }
        public void setRoles(Set<String> v)          { this.roles = v; }
        public LocalDateTime getCreatedAt()          { return createdAt; }
        public void setCreatedAt(LocalDateTime v)    { this.createdAt = v; }
    }

    private UserProfileResponse toProfile(User user) {
        UserProfileResponse r = new UserProfileResponse();
        r.setId(user.getId());
        r.setUsername(user.getUsername());
        r.setEmail(user.getEmail());
        r.setFullName(user.getFullName());
        r.setPhone(user.getPhone());
        r.setCity(user.getCity());
        r.setState(user.getState());
        r.setInstitution(user.getInstitution());
        r.setCategory(user.getCategory());
        r.setTotalScore(user.getTotalScore());
        r.setNationalRank(user.getNationalRank());
        r.setProfileImageUrl(user.getProfileImageUrl());
        r.setRoles(user.getRoles());
        r.setCreatedAt(user.getCreatedAt());
        return r;
    }
}
