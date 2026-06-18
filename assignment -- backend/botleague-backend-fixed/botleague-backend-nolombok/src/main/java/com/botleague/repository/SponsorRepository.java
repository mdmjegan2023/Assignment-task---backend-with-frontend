package com.botleague.repository;

import com.botleague.model.Sponsor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends MongoRepository<Sponsor, String> {
    List<Sponsor> findByActiveTrue();
    List<Sponsor> findByActiveTrueOrderByTierAsc();
}
