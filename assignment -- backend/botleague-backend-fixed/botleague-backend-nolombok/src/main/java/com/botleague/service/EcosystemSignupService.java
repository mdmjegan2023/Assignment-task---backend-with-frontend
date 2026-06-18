package com.botleague.service;

import com.botleague.dto.EcosystemSignupDto;
import com.botleague.exception.BadRequestException;
import com.botleague.model.EcosystemSignup;
import com.botleague.repository.EcosystemSignupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EcosystemSignupService {

    private final EcosystemSignupRepository repository;
    public EcosystemSignupService(EcosystemSignupRepository repository) {
        this.repository = repository;
    }


    public EcosystemSignupDto.Response signup(EcosystemSignupDto.Request request) {
        String normalizedRole = request.getRole().toUpperCase().replace(" ", "_");

        if (request.getEmail() != null &&
            repository.existsByEmailAndRole(request.getEmail(), normalizedRole)) {
            throw new BadRequestException("You have already signed up as a " + request.getRole());
        }

        EcosystemSignup signup = EcosystemSignup.builder()
            .role(normalizedRole)
            .name(request.getName())
            .location(request.getLocation())
            .enroll(request.getEnroll())
            .email(request.getEmail())
            .build();

        return toResponse(repository.save(signup));
    }

    public List<EcosystemSignupDto.Response> getAllByRole(String role) {
        return repository.findByRole(role.toUpperCase().replace(" ", "_")).stream()
            .map(this::toResponse).collect(Collectors.toList());
    }

    private EcosystemSignupDto.Response toResponse(EcosystemSignup s) {
        EcosystemSignupDto.Response r = new EcosystemSignupDto.Response();
        r.setId(s.getId());
        r.setRole(s.getRole());
        r.setName(s.getName());
        r.setLocation(s.getLocation());
        r.setEnroll(s.getEnroll());
        r.setEmail(s.getEmail());
        r.setStatus(s.getStatus());
        r.setCreatedAt(s.getCreatedAt().toString());
        return r;
    }
}
