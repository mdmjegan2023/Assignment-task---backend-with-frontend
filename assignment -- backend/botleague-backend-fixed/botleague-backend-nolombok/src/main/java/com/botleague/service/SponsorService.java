package com.botleague.service;

import com.botleague.exception.ResourceNotFoundException;
import com.botleague.model.Sponsor;
import com.botleague.repository.SponsorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorService {

    private final SponsorRepository sponsorRepository;
    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }


    public List<Sponsor> getActiveSponsors() {
        return sponsorRepository.findByActiveTrueOrderByTierAsc();
    }

    public Sponsor createSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    public void deleteSponsor(String id) {
        Sponsor sponsor = sponsorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Sponsor", id));
        sponsor.setActive(false);
        sponsorRepository.save(sponsor);
    }
}
