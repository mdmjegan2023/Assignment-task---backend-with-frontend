package com.botleague;

import com.botleague.dto.AuthDto;
import com.botleague.exception.BadRequestException;
import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.User;
import com.botleague.repository.UserRepository;
import com.botleague.security.JwtUtils;
import com.botleague.security.UserDetailsImpl;
import com.botleague.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService Unit Tests")
class AuthServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private AuthenticationManager authenticationManager;
    @Mock private JwtUtils jwtUtils;

    @InjectMocks private AuthService authService;

    // ── Shared fixtures ───────────────────────────────────────────────────

    private AuthDto.RegisterRequest validRegisterRequest() {
        AuthDto.RegisterRequest req = new AuthDto.RegisterRequest();
        req.setUsername("testuser");
        req.setEmail("test@example.com");
        req.setPassword("secret123");
        req.setFullName("Test User");
        req.setCity("Chennai");
        req.setState("TN");
        return req;
    }

    private AuthDto.LoginRequest validLoginRequest() {
        AuthDto.LoginRequest req = new AuthDto.LoginRequest();
        req.setUsernameOrEmail("testuser");
        req.setPassword("secret123");
        return req;
    }

    private User savedUser() {
        return User.builder()
                .id("user-001")
                .username("testuser")
                .email("test@example.com")
                .password("$2a$10$hashedpassword")
                .fullName("Test User")
                .roles(Set.of("USER"))
                .enabled(true)
                .build();
    }

    private UserDetailsImpl userDetails(User user) {
        return UserDetailsImpl.build(user);
    }

    // ── Register ──────────────────────────────────────────────────────────

    @Nested
    @DisplayName("register()")
    class Register {

        @Test
        @DisplayName("returns AuthResponse with tokens on successful registration")
        void success() {
            User user = savedUser();
            AuthDto.RegisterRequest req = validRegisterRequest();

            when(userRepository.existsByUsername("testuser")).thenReturn(false);
            when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
            when(passwordEncoder.encode("secret123")).thenReturn("$2a$10$hashedpassword");
            when(userRepository.save(any(User.class))).thenReturn(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails(user), null, List.of());
            when(authenticationManager.authenticate(any())).thenReturn(auth);
            when(jwtUtils.generateAccessToken(auth)).thenReturn("access-token-123");
            when(jwtUtils.generateRefreshToken("testuser")).thenReturn("refresh-token-456");

            AuthDto.AuthResponse response = authService.register(req);

            assertThat(response.getAccessToken()).isEqualTo("access-token-123");
            assertThat(response.getRefreshToken()).isEqualTo("refresh-token-456");
            assertThat(response.getUsername()).isEqualTo("testuser");
            assertThat(response.getEmail()).isEqualTo("test@example.com");
            assertThat(response.getFullName()).isEqualTo("Test User");
            assertThat(response.getRoles()).contains("USER");
            assertThat(response.getTokenType()).isEqualTo("Bearer");

            verify(userRepository).save(any(User.class));
        }

        @Test
        @DisplayName("throws BadRequestException when username is already taken")
        void duplicateUsername() {
            when(userRepository.existsByUsername("testuser")).thenReturn(true);

            assertThatThrownBy(() -> authService.register(validRegisterRequest()))
                    .isInstanceOf(BadRequestException.class)
                    .hasMessage("Username is already taken");

            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("throws BadRequestException when email is already registered")
        void duplicateEmail() {
            when(userRepository.existsByUsername("testuser")).thenReturn(false);
            when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

            assertThatThrownBy(() -> authService.register(validRegisterRequest()))
                    .isInstanceOf(BadRequestException.class)
                    .hasMessage("Email is already registered");

            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("encodes the password before saving")
        void passwordIsEncoded() {
            User user = savedUser();
            AuthDto.RegisterRequest req = validRegisterRequest();

            when(userRepository.existsByUsername(any())).thenReturn(false);
            when(userRepository.existsByEmail(any())).thenReturn(false);
            when(passwordEncoder.encode("secret123")).thenReturn("HASHED");
            when(userRepository.save(any(User.class))).thenReturn(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails(user), null, List.of());
            when(authenticationManager.authenticate(any())).thenReturn(auth);
            when(jwtUtils.generateAccessToken(any())).thenReturn("token");
            when(jwtUtils.generateRefreshToken(any())).thenReturn("rtoken");

            authService.register(req);

            verify(passwordEncoder).encode("secret123");
            verify(userRepository).save(argThat(u -> "HASHED".equals(u.getPassword())));
        }

        @Test
        @DisplayName("saves user with role USER and enabled=true")
        void savedUserHasCorrectDefaults() {
            User user = savedUser();
            AuthDto.RegisterRequest req = validRegisterRequest();

            when(userRepository.existsByUsername(any())).thenReturn(false);
            when(userRepository.existsByEmail(any())).thenReturn(false);
            when(passwordEncoder.encode(any())).thenReturn("HASHED");
            when(userRepository.save(any(User.class))).thenReturn(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails(user), null, List.of());
            when(authenticationManager.authenticate(any())).thenReturn(auth);
            when(jwtUtils.generateAccessToken(any())).thenReturn("t");
            when(jwtUtils.generateRefreshToken(any())).thenReturn("r");

            authService.register(req);

            verify(userRepository).save(argThat(u ->
                    u.isEnabled() && u.getRoles().contains("USER")
            ));
        }
    }

    // ── Login ─────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("login()")
    class Login {

        @Test
        @DisplayName("returns AuthResponse with tokens on successful login")
        void success() {
            User user = savedUser();
            UserDetailsImpl details = userDetails(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    details, null, details.getAuthorities());
            when(authenticationManager.authenticate(any())).thenReturn(auth);
            when(userRepository.findById("user-001")).thenReturn(Optional.of(user));
            when(jwtUtils.generateAccessToken(auth)).thenReturn("access-token-abc");
            when(jwtUtils.generateRefreshToken("testuser")).thenReturn("refresh-token-xyz");

            AuthDto.AuthResponse response = authService.login(validLoginRequest());

            assertThat(response.getAccessToken()).isEqualTo("access-token-abc");
            assertThat(response.getRefreshToken()).isEqualTo("refresh-token-xyz");
            assertThat(response.getUserId()).isEqualTo("user-001");
            assertThat(response.getUsername()).isEqualTo("testuser");
            assertThat(response.getEmail()).isEqualTo("test@example.com");
            assertThat(response.getRoles()).contains("USER");
        }

        @Test
        @DisplayName("propagates BadCredentialsException from AuthenticationManager")
        void badCredentials() {
            when(authenticationManager.authenticate(any()))
                    .thenThrow(new BadCredentialsException("Bad credentials"));

            assertThatThrownBy(() -> authService.login(validLoginRequest()))
                    .isInstanceOf(BadCredentialsException.class);

            verify(userRepository, never()).findById(any());
        }

        @Test
        @DisplayName("throws ResourceNotFoundException when user record cannot be found after auth")
        void userNotFoundAfterAuth() {
            User user = savedUser();
            UserDetailsImpl details = userDetails(user);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    details, null, details.getAuthorities());
            when(authenticationManager.authenticate(any())).thenReturn(auth);
            when(userRepository.findById("user-001")).thenReturn(Optional.empty());

            assertThatThrownBy(() -> authService.login(validLoginRequest()))
                    .isInstanceOf(ResourceNotFoundException.class);
        }

        @Test
        @DisplayName("accepts login with email as usernameOrEmail")
        void loginWithEmail() {
            User user = savedUser();
            UserDetailsImpl details = userDetails(user);

            AuthDto.LoginRequest req = new AuthDto.LoginRequest();
            req.setUsernameOrEmail("test@example.com");
            req.setPassword("secret123");

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    details, null, details.getAuthorities());
            when(authenticationManager.authenticate(
                    argThat(t -> "test@example.com".equals(
                            ((UsernamePasswordAuthenticationToken) t).getPrincipal()))
            )).thenReturn(auth);
            when(userRepository.findById("user-001")).thenReturn(Optional.of(user));
            when(jwtUtils.generateAccessToken(any())).thenReturn("tok");
            when(jwtUtils.generateRefreshToken(any())).thenReturn("rtok");

            AuthDto.AuthResponse response = authService.login(req);

            assertThat(response.getEmail()).isEqualTo("test@example.com");
        }
    }
}
