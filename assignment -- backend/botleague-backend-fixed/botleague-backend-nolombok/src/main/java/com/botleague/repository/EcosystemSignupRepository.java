package com.botleague.repository;

import com.botleague.model.EcosystemSignup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcosystemSignupRepository extends MongoRepository<EcosystemSignup, String> {
    List<EcosystemSignup> findByRole(String role);
    List<EcosystemSignup> findByStatus(String status);
    boolean existsByEmailAndRole(String email, String role);
}
