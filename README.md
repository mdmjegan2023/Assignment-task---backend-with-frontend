# BotLeague – India's Ultimate Robotics Arena

A full-stack competitive robotics platform built with **React + Vite** (frontend) and **Spring Boot + MongoDB** (backend).

---

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
  - [Frontend Structure](#frontend-structure)
  - [Backend Structure](#backend-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Frontend Setup](#frontend-setup)
  - [Backend Setup](#backend-setup)
- [Frontend Sections](#frontend-sections)
- [Design Tokens](#design-tokens)
- [Key Layout Notes](#key-layout-notes)
- [Assets](#assets)
- [Backend Configuration](#backend-configuration)
- [API Endpoints](#api-endpoints)
  - [Auth](#auth----apiauth)
  - [Users](#users----apiusers)
  - [Events](#events----apievents)
  - [Teams](#teams----apiteams)
  - [Registrations](#registrations----apiregistrations)
  - [Leaderboard](#leaderboard----apileaderboard)
  - [Ecosystem Signup](#ecosystem-signup----apiecosystem)
  - [Sponsors](#sponsors----apisponsors)
  - [Brackets](#brackets----apibrackets)
- [Connecting Frontend to Backend](#connecting-frontend-to-backend)
- [License](#license)

---

## Project Overview

BotLeague is India's national competitive robotics ecosystem. The platform includes:

- A React landing page showcasing competitions, categories, disciplines, rankings, and community features.
- A Spring Boot REST API backend handling authentication, event management, team registration, leaderboards, and more.

---

## Tech Stack

### Frontend
| Technology | Purpose |
|---|---|
| React 18 | UI library |
| Vite | Build tool and dev server |
| CSS Modules | Per-component scoped styles |
| react-icons | Icon library (FontAwesome subset) |

### Backend
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

## Project Structure

### Frontend Structure

```
botleague/
├── public/
│   └── assets/              # All images and logos
├── src/
│   ├── components/
│   │   ├── Navbar.jsx / .css
│   │   ├── Hero.jsx / .css
│   │   ├── CompetitionsEvents.jsx / .css
│   │   ├── UserJourney.jsx / .css
│   │   ├── WhatIsBotLeague.jsx / .css
│   │   ├── Categories.jsx / .css
│   │   ├── Disciplines.jsx / .css
│   │   ├── WhyRegister.jsx / .css
│   │   ├── Sponsors.jsx / .css
│   │   ├── JoinEcosystem.jsx / .css
│   │   └── Footer.jsx / .css
│   ├── App.jsx
│   ├── main.jsx
│   └── index.css
├── index.html
├── package.json
├── vite.config.js
└── README.md
```

### Backend Structure

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

## Getting Started

### Prerequisites

| Requirement | Version |
|---|---|
| Node.js | v18 or higher |
| npm | v8 or higher |
| Java | 17+ |
| Maven | 3.8+ |
| MongoDB | Running locally on `localhost:27017` |

---

### Frontend Setup

```bash
# 1. Navigate to the frontend directory
cd botleague

# 2. Install dependencies
npm install

# 3. Start the development server
npm run dev
```

Open [http://localhost:5173](http://localhost:5173) in your browser.

**Build for Production:**
```bash
npm run build
```
Output goes to the `dist/` folder.

**Preview Production Build:**
```bash
npm run preview
```

---

### Backend Setup

```bash
# Navigate to the backend directory
cd botleague-backend

# Run the application
mvn spring-boot:run
```

Server starts on **http://localhost:8080**

On first run, the `DataSeeder` automatically creates:
- **Admin user** → `admin` / `Admin@1234`
- Sample sponsors, events, and leaderboard entries

---

## Frontend Sections

| Section | Component | Description |
|---|---|---|
| Navigation | `Navbar` | Top nav with logo, links, Login & Register buttons |
| Hero | `Hero` | Full-bleed banner with live event badge and CTAs |
| Competitions & Events | `CompetitionsEvents` | Live Now / Upcoming / Past Results tabs |
| User Journey | `UserJourney` | 4-step path: Build Team → Compete → Rank → Join |
| What is BotLeague? | `WhatIsBotLeague` | 4-point feature breakdown with illustration |
| Categories | `Categories` | Mini Makers, Junior Innovators, Young Engineers, Robo Minds |
| Competition Disciplines | `Disciplines` | 6 sport cards in a 4+2 grid layout |
| Why Register | `WhyRegister` | League advantages with leaderboard preview |
| Join the Ecosystem | `JoinEcosystem` | Sign-up forms for Judges, Volunteers, Community |
| Sponsors | `Sponsors` | Logo grid of institutional partners |
| Footer | `Footer` | Quick links and social media icons |

---

## Design Tokens

Global CSS variables defined in `src/index.css`:

| Variable | Value | Usage |
|---|---|---|
| `--red` | `#e8001a` | Primary accent, CTAs |
| `--blue` | `#0090ff` | Connectors, highlights |
| `--bg-card` | `#111` | Card backgrounds |
| `--font-display` | `'Barlow Condensed'` | Headings |
| `--text-secondary` | `#888` | Muted text |

---

## Key Layout Notes

- **Competition Disciplines** grid: 4 cards on row 1, 2 cards centered on row 2 (responsive: 2-col on tablet, 1-col on mobile).
- **Categories** grid: 4 equal cards in a single row.
- **Sponsors** grid: 3 columns, 2 rows (6 sponsors total).

---

## Assets

All images live in `public/assets/`:

| File | Used In |
|---|---|
| `hero-bg.jpg` | Hero background |
| `circuit-bg.png` | Section backgrounds |
| `whatis-illustration.png` | What Is BotLeague section |
| `disc-roborace.jpg` | Disciplines – Robo Race |
| `disc-linefollower.jpg` | Disciplines – Line Follower |
| `disc-rcracing.jpg` | Disciplines – RC Racing |
| `disc-drone.jpg` | Disciplines – FPV Drone |
| `disc-robohockey.jpg` | Disciplines – Robo Hockey |
| `disc-robowar.png` | Disciplines – Robo War |
| `logo-nitdelhi.png` | Sponsors |
| `logo-indianbit.png` | Sponsors |
| `logo-nitsilchar.png` | Sponsors |
| `logo-delhivery.png` | Sponsors |
| `logo-iitbombay.png` | Sponsors |
| `logo-generalrobotics.png` | Sponsors |

---

## Backend Configuration

Edit `src/main/resources/application.properties`:

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

### Events — `/api/events`

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

## Connecting Frontend to Backend

Add this to your Vite frontend `.env` file:
```
VITE_API_BASE_URL=http://localhost:8080
```

All API calls require the following header for protected routes:
```
Authorization: Bearer <accessToken>
```

---

## License

All rights reserved – BotLeague 2025.
