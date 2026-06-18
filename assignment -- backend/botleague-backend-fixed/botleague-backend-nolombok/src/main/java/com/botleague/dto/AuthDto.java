package com.botleague.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class AuthDto {

    public static class RegisterRequest {
        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 30, message = "Username must be 3-30 characters")
        private String username;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;

        @NotBlank(message = "Full name is required")
        private String fullName;

        private String phone, city, state, institution, category;

        public String getUsername()              { return username; }
        public void setUsername(String v)        { this.username = v; }
        public String getEmail()                 { return email; }
        public void setEmail(String v)           { this.email = v; }
        public String getPassword()              { return password; }
        public void setPassword(String v)        { this.password = v; }
        public String getFullName()              { return fullName; }
        public void setFullName(String v)        { this.fullName = v; }
        public String getPhone()                 { return phone; }
        public void setPhone(String v)           { this.phone = v; }
        public String getCity()                  { return city; }
        public void setCity(String v)            { this.city = v; }
        public String getState()                 { return state; }
        public void setState(String v)           { this.state = v; }
        public String getInstitution()           { return institution; }
        public void setInstitution(String v)     { this.institution = v; }
        public String getCategory()              { return category; }
        public void setCategory(String v)        { this.category = v; }
    }

    public static class LoginRequest {
        @NotBlank(message = "Username or email is required")
        private String usernameOrEmail;

        @NotBlank(message = "Password is required")
        private String password;

        public String getUsernameOrEmail()       { return usernameOrEmail; }
        public void setUsernameOrEmail(String v) { this.usernameOrEmail = v; }
        public String getPassword()              { return password; }
        public void setPassword(String v)        { this.password = v; }
    }

    public static class AuthResponse {
        private String accessToken;
        private String refreshToken;
        private String tokenType = "Bearer";
        private String userId, username, email, fullName;
        private Set<String> roles;

        public String getAccessToken()           { return accessToken; }
        public void setAccessToken(String v)     { this.accessToken = v; }
        public String getRefreshToken()          { return refreshToken; }
        public void setRefreshToken(String v)    { this.refreshToken = v; }
        public String getTokenType()             { return tokenType; }
        public void setTokenType(String v)       { this.tokenType = v; }
        public String getUserId()                { return userId; }
        public void setUserId(String v)          { this.userId = v; }
        public String getUsername()              { return username; }
        public void setUsername(String v)        { this.username = v; }
        public String getEmail()                 { return email; }
        public void setEmail(String v)           { this.email = v; }
        public String getFullName()              { return fullName; }
        public void setFullName(String v)        { this.fullName = v; }
        public Set<String> getRoles()            { return roles; }
        public void setRoles(Set<String> v)      { this.roles = v; }
    }

    public static class RefreshTokenRequest {
        @NotBlank
        private String refreshToken;
        public String getRefreshToken()          { return refreshToken; }
        public void setRefreshToken(String v)    { this.refreshToken = v; }
    }

    public static class ForgotPasswordRequest {
        @NotBlank @Email
        private String email;
        public String getEmail()                 { return email; }
        public void setEmail(String v)           { this.email = v; }
    }

    public static class ResetPasswordRequest {
        @NotBlank
        private String token;
        @NotBlank @Size(min = 6)
        private String newPassword;
        public String getToken()                 { return token; }
        public void setToken(String v)           { this.token = v; }
        public String getNewPassword()           { return newPassword; }
        public void setNewPassword(String v)     { this.newPassword = v; }
    }
}
