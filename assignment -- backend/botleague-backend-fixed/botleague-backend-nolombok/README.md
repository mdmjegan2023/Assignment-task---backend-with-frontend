# BotLeague Backend — Spring Boot + MongoDB

India's Ultimate Robotics Arena — REST API backend.

---

## Tech Stack
| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.2.5 |
| Database | MongoDB |
| Auth | JWT (jjwt 0.11.5) |
| Security | Spring Security 6 |
| Validation | Jakarta Validation |
| Build | Maven |
| Java | 17 |

---

## Prerequisites
- Java 17+
- Maven 3.8+
- MongoDB running locally on `localhost:27017`

---

## Run the App

```bash
cd botleague-backend
mvn spring-boot:run
```

Server starts on **http://localhost:8080**

On first run the `DataSeeder` automatically creates:
- **Admin user** → `admin` / `Admin@1234`
- Sample sponsors, events, and leaderboard entries

---

## Configuration (`src/main/resources/application.properties`)

| Key | Default | Description |
|---|---|---|
| `spring.data.mongodb.uri` | `mongodb://localhost:27017/botleague` | MongoDB connection |
| `app.jwt.secret` | (set in props) | 256-bit signing key |
| `app.jwt.expiration-ms` | `86400000` (24 h) | Access token TTL |
| `app.jwt.refresh-expiration-ms` | `604800000` (7 d) | Refresh token TTL |
| `app.cors.allowed-origins` | `http://localhost:5173,...` | Vite dev server |

---

## API Endpoints

### Auth — `/api/auth`
| Method | Path | Auth | Description |
|---|---|---|---|
| POST | `/register` | ❌ | Register new user |
| POST | `/login` | ❌ | Login → JWT tokens |
| POST | `/refresh` | ❌ | Refresh access token |
| POST | `/forgot-password` | ❌ | Send reset email |
| POST | `/reset-password` | ❌ | Reset password with token |

**Register body:**
```json
{
  "username": "roboteer1",
  "email": "user@example.com",
  "password": "secret123",
  "fullName": "Arjun Sharma",
  "phone": "9876543210",
  "city": "Mumbai",
  "state": "Maharashtra",
  "institution": "IIT Bombay",
  "category": "Robo Minds"
}
```

**Login body:**
```json
{ "usernameOrEmail": "roboteer1", "password": "secret123" }
```

**Auth response:**
```json
{
  "accessToken": "<JWT>",
  "refreshToken": "<JWT>",
  "tokenType": "Bearer",
  "userId": "...",
  "username": "roboteer1",
  "email": "user@example.com",
  "fullName": "Arjun Sharma",
  "roles": ["USER"]
}
```

---

### Users — `/api/users`
| Method | Path | Auth | Description |
|---|---|---|---|
| GET | `/me` | ✅ USER | Get own profile |
| PUT | `/me` | ✅ USER | Update own profile |
| PATCH | `/me/password` | ✅ USER | Change password |
| GET | `/{id}` | ✅ ADMIN | Get any user by id |

---

### Events — `/api/events/public` & `/api/admin/events`
| Method | Path | Auth | Description |
|---|---|---|---|
| GET | `/api/events/public/all` | ❌ | All events |
| GET | `/api/events/public/live` | ❌ | Live events |
| GET | `/api/events/public/upcoming` | ❌ | Upcoming events |
| GET | `/api/events/public/past` | ❌ | Past events |
| GET | `/api/events/public/{id}` | ❌ | Event detail |
| POST | `/api/admin/events` | ✅ ADMIN | Create event |
| PUT | `/api/admin/events/{id}` | ✅ ADMIN | Update event |
| DELETE | `/api/admin/events/{id}` | ✅ ADMIN | Delete event |

**Event status values:** `LIVE` / `UPCOMING` / `PAST`

**Discipline values:** `Robo Race` / `Line Follower` / `RC Racing` / `FPV Drone Racing & Aeromodelling` / `Robo Hockey` / `Robo War`

**Category values:** `Mini Makers` / `Junior Innovators` / `Young Engineers` / `Robo Minds`

---

### Teams — `/api/teams`
| Method | Path | Auth | Description |
|---|---|---|---|
| POST | `/` | ✅ USER | Create team (caller becomes captain) |
| GET | `/` | ✅ USER | List all teams (`?category=`) |
| GET | `/my` | ✅ USER | Teams I belong to |
| GET | `/{id}` | ✅ USER | Team detail |
| PUT | `/{id}` | ✅ USER | Update team (captain only) |
| POST | `/{id}/members` | ✅ USER | Add member (captain only) |
| DELETE | `/{id}/members/{memberId}` | ✅ USER | Remove member (captain only) |

---

### Registrations — `/api/registrations`
| Method | Path | Auth | Description |
|---|---|---|---|
| POST | `/` | ✅ USER | Register for an event |
| GET | `/my` | ✅ USER | My registrations |
| DELETE | `/{id}` | ✅ USER | Cancel registration |

**Register body:**
```json
{
  "eventId": "...",
  "registrationType": "USER"
}
```
For team registration: add `"registrationType": "TEAM"` and `"teamId": "..."`.

---

### Leaderboard — `/api/leaderboard`
| Method | Path | Auth | Description |
|---|---|---|---|
| GET | `/` | ❌ | Paginated leaderboard (`?entityType=USER&category=Robo+Minds&page=0&size=20`) |
| GET | `/top10` | ❌ | Top 10 (`?entityType=USER`) |

---

### Ecosystem Signup — `/api/ecosystem`
| Method | Path | Auth | Description |
|---|---|---|---|
| POST | `/signup` | ❌ | Sign up as Judge/Volunteer/Community Member |
| GET | `/` | ✅ ADMIN | List signups by role |

**Signup body:**
```json
{
  "role": "JUDGE",
  "name": "Priya Mehta",
  "location": "Delhi",
  "enroll": "IIT Delhi — Robotics Dept.",
  "email": "priya@example.com"
}
```
**Role values:** `JUDGE` / `VOLUNTEER` / `COMMUNITY_MEMBER`

---

### Sponsors — `/api/sponsors`
| Method | Path | Auth | Description |
|---|---|---|---|
| GET | `/` | ❌ | Active sponsors (sorted by tier) |
| POST | `/` | ✅ ADMIN | Add sponsor |
| DELETE | `/{id}` | ✅ ADMIN | Deactivate sponsor |

---

### Brackets — `/api/brackets`
| Method | Path | Auth | Description |
|---|---|---|---|
| GET | `/event/{eventId}` | ❌ | Full bracket for an event |
| POST | `/` | ✅ ADMIN | Create a match slot |
| PATCH | `/{matchId}/result` | ✅ ADMIN | Record match result |

---

## Project Structure
```
src/main/java/com/botleague/
├── BotLeagueApplication.java
├── config/
│   ├── DataSeeder.java       ← seeds default admin + sample data
│   ├── MongoConfig.java      ← removes _class field
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── BracketController.java
│   ├── EcosystemSignupController.java
│   ├── EventController.java
│   ├── LeaderboardController.java
│   ├── RegistrationController.java
│   ├── SponsorController.java
│   ├── TeamController.java
│   └── UserController.java
├── dto/
│   ├── ApiResponse.java
│   ├── AuthDto.java
│   ├── EcosystemSignupDto.java
│   ├── EventDto.java
│   ├── RegistrationDto.java
│   └── TeamDto.java
├── exception/
│   ├── BadRequestException.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── model/
│   ├── BracketMatch.java
│   ├── EcosystemSignup.java
│   ├── Event.java
│   ├── LeaderboardEntry.java
│   ├── Registration.java
│   ├── Sponsor.java
│   ├── Team.java
│   └── User.java
├── repository/
│   ├── BracketMatchRepository.java
│   ├── EcosystemSignupRepository.java
│   ├── EventRepository.java
│   ├── LeaderboardRepository.java
│   ├── RegistrationRepository.java
│   ├── SponsorRepository.java
│   ├── TeamRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtAuthFilter.java
│   ├── JwtUtils.java
│   ├── UserDetailsImpl.java
│   └── UserDetailsServiceImpl.java
└── service/
    ├── AuthService.java
    ├── BracketService.java
    ├── EcosystemSignupService.java
    ├── EventService.java
    ├── LeaderboardService.java
    ├── RegistrationService.java
    ├── SponsorService.java
    └── TeamService.java
```

---

## Connecting the Frontend

Add this to your Vite frontend `.env`:
```
VITE_API_BASE_URL=http://localhost:8080
```

All API calls require the header:
```
Authorization: Bearer <accessToken>
```
for protected routes.
