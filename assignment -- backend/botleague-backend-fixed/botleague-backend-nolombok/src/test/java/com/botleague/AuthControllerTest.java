package com.botleague;

import com.botleague.controller.AuthController;
import com.botleague.dto.AuthDto;
import com.botleague.exception.BadRequestException;
import com.botleague.exception.GlobalExceptionHandler;
import com.botleague.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("AuthController Web Layer Tests")
class AuthControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean AuthService authService;

    // ── Helpers ───────────────────────────────────────────────────────────

    private String json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    private AuthDto.AuthResponse stubResponse() {
        AuthDto.AuthResponse r = new AuthDto.AuthResponse();
        r.setAccessToken("access-token-xyz");
        r.setRefreshToken("refresh-token-abc");
        r.setUserId("user-001");
        r.setUsername("testuser");
        r.setEmail("test@example.com");
        r.setFullName("Test User");
        r.setRoles(Set.of("USER"));
        return r;
    }

    // ── Register ──────────────────────────────────────────────────────────

    @Nested
    @DisplayName("POST /api/auth/register")
    class RegisterEndpoint {

        private java.util.Map<String, Object> validBody() {
            return java.util.Map.of(
                    "username", "testuser",
                    "email", "test@example.com",
                    "password", "secret123",
                    "fullName", "Test User"
            );
        }

        @Test
        @DisplayName("201 Created with auth response on valid request")
        @WithMockUser
        void register_valid_returns201() throws Exception {
            when(authService.register(any())).thenReturn(stubResponse());

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(validBody())))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.message").value("Registration successful"))
                    .andExpect(jsonPath("$.data.accessToken").value("access-token-xyz"))
                    .andExpect(jsonPath("$.data.refreshToken").value("refresh-token-abc"))
                    .andExpect(jsonPath("$.data.username").value("testuser"))
                    .andExpect(jsonPath("$.data.email").value("test@example.com"))
                    .andExpect(jsonPath("$.data.tokenType").value("Bearer"));
        }

        @Test
        @DisplayName("400 Bad Request when username is already taken")
        @WithMockUser
        void register_duplicateUsername_returns400() throws Exception {
            when(authService.register(any()))
                    .thenThrow(new BadRequestException("Username is already taken"));

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(validBody())))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.success").value(false))
                    .andExpect(jsonPath("$.message").value("Username is already taken"));
        }

        @Test
        @DisplayName("400 Bad Request when email is already registered")
        @WithMockUser
        void register_duplicateEmail_returns400() throws Exception {
            when(authService.register(any()))
                    .thenThrow(new BadRequestException("Email is already registered"));

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(validBody())))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.success").value(false))
                    .andExpect(jsonPath("$.message").value("Email is already registered"));
        }

        @Test
        @DisplayName("400 Bad Request when username is blank")
        @WithMockUser
        void register_blankUsername_returns400() throws Exception {
            java.util.Map<String, Object> body = new java.util.HashMap<>(validBody());
            body.put("username", "");

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.username").exists());
        }

        @Test
        @DisplayName("400 Bad Request when username is too short (< 3 chars)")
        @WithMockUser
        void register_shortUsername_returns400() throws Exception {
            java.util.Map<String, Object> body = new java.util.HashMap<>(validBody());
            body.put("username", "ab");

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.username").exists());
        }

        @Test
        @DisplayName("400 Bad Request when email format is invalid")
        @WithMockUser
        void register_invalidEmail_returns400() throws Exception {
            java.util.Map<String, Object> body = new java.util.HashMap<>(validBody());
            body.put("email", "not-an-email");

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.email").exists());
        }

        @Test
        @DisplayName("400 Bad Request when password is too short (< 6 chars)")
        @WithMockUser
        void register_shortPassword_returns400() throws Exception {
            java.util.Map<String, Object> body = new java.util.HashMap<>(validBody());
            body.put("password", "123");

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.password").exists());
        }

        @Test
        @DisplayName("400 Bad Request when fullName is blank")
        @WithMockUser
        void register_blankFullName_returns400() throws Exception {
            java.util.Map<String, Object> body = new java.util.HashMap<>(validBody());
            body.put("fullName", "");

            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.fullName").exists());
        }

        @Test
        @DisplayName("400 Bad Request when request body is missing entirely")
        @WithMockUser
        void register_emptyBody_returns400() throws Exception {
            mockMvc.perform(post("/api/auth/register")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest());

            verify(authService, never()).register(any());
        }
    }

    // ── Login ─────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("POST /api/auth/login")
    class LoginEndpoint {

        private java.util.Map<String, String> validBody() {
            return java.util.Map.of(
                    "usernameOrEmail", "testuser",
                    "password", "secret123"
            );
        }

        @Test
        @DisplayName("200 OK with auth response on valid credentials")
        @WithMockUser
        void login_valid_returns200() throws Exception {
            when(authService.login(any())).thenReturn(stubResponse());

            mockMvc.perform(post("/api/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(validBody())))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.message").value("Login successful"))
                    .andExpect(jsonPath("$.data.accessToken").value("access-token-xyz"))
                    .andExpect(jsonPath("$.data.refreshToken").value("refresh-token-abc"))
                    .andExpect(jsonPath("$.data.username").value("testuser"))
                    .andExpect(jsonPath("$.data.tokenType").value("Bearer"));
        }

        @Test
        @DisplayName("401 Unauthorized on wrong password")
        @WithMockUser
        void login_badPassword_returns401() throws Exception {
            when(authService.login(any()))
                    .thenThrow(new BadCredentialsException("Bad credentials"));

            mockMvc.perform(post("/api/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(validBody())))
                    .andExpect(status().isUnauthorized())
                    .andExpect(jsonPath("$.success").value(false))
                    .andExpect(jsonPath("$.message").value("Invalid username or password"));
        }

        @Test
        @DisplayName("400 Bad Request when usernameOrEmail is blank")
        @WithMockUser
        void login_blankUsernameOrEmail_returns400() throws Exception {
            java.util.Map<String, String> body = new java.util.HashMap<>();
            body.put("usernameOrEmail", "");
            body.put("password", "secret123");

            mockMvc.perform(post("/api/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.usernameOrEmail").exists());

            verify(authService, never()).login(any());
        }

        @Test
        @DisplayName("400 Bad Request when password is blank")
        @WithMockUser
        void login_blankPassword_returns400() throws Exception {
            java.util.Map<String, String> body = new java.util.HashMap<>();
            body.put("usernameOrEmail", "testuser");
            body.put("password", "");

            mockMvc.perform(post("/api/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.data.password").exists());

            verify(authService, never()).login(any());
        }

        @Test
        @DisplayName("200 OK when logging in with email instead of username")
        @WithMockUser
        void login_withEmail_returns200() throws Exception {
            AuthDto.AuthResponse resp = stubResponse();
            when(authService.login(any())).thenReturn(resp);

            java.util.Map<String, String> body = java.util.Map.of(
                    "usernameOrEmail", "test@example.com",
                    "password", "secret123"
            );

            mockMvc.perform(post("/api/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json(body)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.data.email").value("test@example.com"));
        }

        @Test
        @DisplayName("400 Bad Request when request body is empty")
        @WithMockUser
        void login_emptyBody_returns400() throws Exception {
            mockMvc.perform(post("/api/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest());

            verify(authService, never()).login(any());
        }
    }
}
