package com.botleague.config;

import com.botleague.model.*;
import com.botleague.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final UserRepository userRepository;
    private final SponsorRepository sponsorRepository;
    private final EventRepository eventRepository;
    private final LeaderboardRepository leaderboardRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, SponsorRepository sponsorRepository,
                      EventRepository eventRepository, LeaderboardRepository leaderboardRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.sponsorRepository = sponsorRepository;
        this.eventRepository = eventRepository;
        this.leaderboardRepository = leaderboardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedAdmin();
        seedSponsors();
        seedEvents();
        seedLeaderboard();
    }

    private void seedAdmin() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                .username("admin")
                .email("admin@botleague.in")
                .password(passwordEncoder.encode("Admin@1234"))
                .fullName("BotLeague Admin")
                .roles(Set.of("USER", "ADMIN"))
                .enabled(true)
                .emailVerified(true)
                .build();
            userRepository.save(admin);
            log.info("Default admin created  →  username: admin  |  password: Admin@1234");
        }
    }

    private void seedSponsors() {
        if (sponsorRepository.count() == 0) {
            List<Sponsor> sponsors = List.of(
                Sponsor.builder().name("IIT Bombay").logoUrl("/assets/logo-iitbombay.png").tier("TITLE").build(),
                Sponsor.builder().name("NIT Silchar").logoUrl("/assets/logo-nitsilchar.png").tier("GOLD").build(),
                Sponsor.builder().name("NIT Delhi").logoUrl("/assets/logo-nitdelhi.png").tier("GOLD").build(),
                Sponsor.builder().name("Delhivery").logoUrl("/assets/logo-delhivery.png").tier("SILVER").build(),
                Sponsor.builder().name("IndianBit").logoUrl("/assets/logo-indianbit.png").tier("SILVER").build(),
                Sponsor.builder().name("General Robotics").logoUrl("/assets/logo-generalrobotics.png").tier("BRONZE").build()
            );
            sponsorRepository.saveAll(sponsors);
            log.info("Sponsors seeded");
        }
    }

    private void seedEvents() {
        if (eventRepository.count() == 0) {
            List<Event> events = List.of(
                Event.builder()
                    .title("Bengaluru Regionals")
                    .description("The season-opening regional qualifier hosted in Bengaluru.")
                    .city("Bengaluru").location("Koramangala Indoor Stadium")
                    .eventDate(LocalDate.now().minusDays(5))
                    .status("LIVE").discipline("Robo Race").category("Robo Minds")
                    .capacity(32).streamUrl("https://youtube.com/live/botleague").episode("14")
                    .build(),
                Event.builder()
                    .title("Mumbai Regionals").description("Western-zone qualifier at BKC.")
                    .city("Mumbai").location("BKC").eventDate(LocalDate.of(2025, 11, 11))
                    .status("UPCOMING").discipline("Line Follower").category("Young Engineers")
                    .capacity(32).build(),
                Event.builder()
                    .title("Delhi Regionals").description("Northern-zone qualifier.")
                    .city("Delhi").location("BKC").eventDate(LocalDate.of(2025, 11, 11))
                    .status("UPCOMING").discipline("Robo War").category("Robo Minds")
                    .capacity(32).build()
            );
            eventRepository.saveAll(events);
            log.info("Sample events seeded");
        }
    }

    private void seedLeaderboard() {
        if (leaderboardRepository.count() == 0) {
            List<LeaderboardEntry> entries = List.of(
                LeaderboardEntry.builder().entityType("USER").entityId("seed-1").entityName("Player One")
                    .totalScore(508754).rank(1).eventsParticipated(12).wins(9).avatarColor("gold").build(),
                LeaderboardEntry.builder().entityType("USER").entityId("seed-2").entityName("Player Two")
                    .totalScore(22000).rank(2).eventsParticipated(10).wins(7).avatarColor("blue").build(),
                LeaderboardEntry.builder().entityType("USER").entityId("seed-3").entityName("Player Three")
                    .totalScore(20030).rank(3).eventsParticipated(9).wins(6).avatarColor("blue").build(),
                LeaderboardEntry.builder().entityType("USER").entityId("seed-4").entityName("Player Four")
                    .totalScore(19500).rank(4).eventsParticipated(8).wins(5).avatarColor("pink").build(),
                LeaderboardEntry.builder().entityType("USER").entityId("seed-5").entityName("Player Five")
                    .totalScore(15060).rank(5).eventsParticipated(7).wins(4).avatarColor("pink").build()
            );
            leaderboardRepository.saveAll(entries);
            log.info("Sample leaderboard seeded");
        }
    }
}
