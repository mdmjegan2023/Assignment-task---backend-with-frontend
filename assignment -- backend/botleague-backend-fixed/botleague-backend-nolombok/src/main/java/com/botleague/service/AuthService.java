package com.botleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.botleague.dto.AuthDto;
import com.botleague.exception.BadRequestException;
import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.User;
import com.botleague.repository.UserRepository;
import com.botleague.security.JwtUtils;
import com.botleague.security.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    public AuthDto.AuthResponse register(AuthDto.RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already registered");
        }

        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .fullName(request.getFullName())
            .phone(request.getPhone())
            .city(request.getCity())
            .state(request.getState())
            .institution(request.getInstitution())
            .category(request.getCategory())
            .roles(Set.of("USER"))
            .enabled(true)
            .emailVerified(false)
            .emailVerificationToken(UUID.randomUUID().toString())
            .build();

        User saved = userRepository.save(user);
        log.info("New user registered: {}", saved.getUsername());

        // Auto-login after registration
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return buildAuthResponse(auth, saved);
    }

    public AuthDto.AuthResponse login(AuthDto.LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsernameOrEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findById(userDetails.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", userDetails.getId()));

        return buildAuthResponse(auth, user);
    }

    public AuthDto.AuthResponse refreshToken(AuthDto.RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        if (!jwtUtils.validateToken(token)) {
            throw new BadRequestException("Invalid or expired refresh token");
        }
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        String newAccess = jwtUtils.generateAccessTokenFromUsername(username);
        String newRefresh = jwtUtils.generateRefreshToken(username);

        AuthDto.AuthResponse response = new AuthDto.AuthResponse();
        response.setAccessToken(newAccess);
        response.setRefreshToken(newRefresh);
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRoles(user.getRoles());
        return response;
    }

    public void forgotPassword(AuthDto.ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("No account with that email"));

        user.setPasswordResetToken(UUID.randomUUID().toString());
        user.setPasswordResetTokenExpiry(LocalDateTime.now().plusHours(1));
        userRepository.save(user);
        // TODO: send email with reset link containing the token
        log.info("Password reset token generated for: {}", user.getEmail());
    }

    public void resetPassword(AuthDto.ResetPasswordRequest request) {
        User user = userRepository.findByPasswordResetToken(request.getToken())
            .orElseThrow(() -> new BadRequestException("Invalid or expired reset token"));

        if (user.getPasswordResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Reset token has expired");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    private AuthDto.AuthResponse buildAuthResponse(Authentication auth, User user) {
        AuthDto.AuthResponse response = new AuthDto.AuthResponse();
        response.setAccessToken(jwtUtils.generateAccessToken(auth));
        response.setRefreshToken(jwtUtils.generateRefreshToken(user.getUsername()));
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRoles(user.getRoles());
        return response;
    }
}
