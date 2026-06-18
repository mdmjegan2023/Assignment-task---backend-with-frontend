# BotLeague – India's Ultimate Robotics Arena

A React + Vite landing page for **BotLeague**, India's national competitive robotics ecosystem. The site showcases competitions, categories, disciplines, rankings, and community features.

---

## Project Structure

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

---

## Getting Started

### Prerequisites
- **Node.js** v18 or higher
- **npm** v8 or higher

### Installation

```bash
# 1. Install dependencies
npm install

# 2. Start the development server
npm run dev
```

Open [http://localhost:5173](http://localhost:5173) in your browser.

### Build for Production

```bash
npm run build
```

Output goes to the `dist/` folder.

### Preview Production Build

```bash
npm run preview
```

---

## Sections

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

## Tech Stack

- **React 18** – UI library
- **Vite** – Build tool and dev server
- **CSS Modules** (per-component `.css` files) – Scoped styles
- **react-icons** – Icon library (FontAwesome subset)

---

## Design Tokens

Global CSS variables are defined in `src/index.css`:

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

## License

All rights reserved – BotLeague 2025.
