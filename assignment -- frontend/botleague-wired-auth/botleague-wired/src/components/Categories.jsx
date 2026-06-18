import React from "react";
import { FaArrowRight } from "react-icons/fa";
import "./Categories.css";

const iconProps = {
  viewBox: "0 0 48 48",
  width: "1em",
  height: "1em",
  fill: "none",
  stroke: "currentColor",
  strokeWidth: 2.3,
  strokeLinecap: "round",
  strokeLinejoin: "round",
};

// M shield/logo icon for Mini Makers
const MiniMakersIcon = () => (
  <svg {...iconProps}>
    <path d="M24 6L10 12v10c0 8 5.5 15.4 14 18 8.5-2.6 14-10 14-18V12L24 6z" />
    <path d="M17 28V20l7 6 7-6v8" />
  </svg>
);

// Lightbulb with rays for Junior Innovators
const JuniorInnovatorsIcon = () => (
  <svg {...iconProps}>
    <path d="M24 8a10 10 0 0 0-6 18.1c1.1.8 1.7 2.1 1.7 3.5V31h8.6v-1.4c0-1.4.6-2.7 1.7-3.5A10 10 0 0 0 24 8z" />
    <path d="M19 37h10M20 41h8" />
    <path d="M24 4v2M10 10l1.4 1.4M38 10l-1.4 1.4M6 24h2M40 24h2" />
  </svg>
);

// Person with gear/settings for Young Engineers
const YoungEngineersIcon = () => (
  <svg {...iconProps}>
    <circle cx="17" cy="14" r="5" />
    <path d="M8 38v-4a9 9 0 0 1 9-9h4" />
    <circle cx="35" cy="26" r="6" />
    <path d="M35 18v-2M35 34v2M27.8 22l-1.4-1.4M42.2 30l1.4 1.4M28 30l-1.4 1.4M42.2 22l1.4-1.4" />
    <circle cx="35" cy="26" r="2.5" fill="currentColor" />
  </svg>
);

// Robot/brain icon for Robo Minds
const RoboMindsIcon = () => (
  <svg {...iconProps}>
    <rect x="13" y="16" width="22" height="18" rx="3" />
    <path d="M24 10v6M18 10h12" />
    <circle cx="24" cy="8" r="2" />
    <path d="M8 22h5M35 22h5M8 30h5M35 30h5" />
    <circle cx="19" cy="24" r="2" />
    <circle cx="29" cy="24" r="2" />
    <path d="M19 30h10" />
  </svg>
);

const cards = [
  { title: "MINI MAKERS",       text: "Where Creativity Meets Logic.",           icon: <MiniMakersIcon />,       highlight: true },
  { title: "JUNIOR INNOVATORS", text: "Engineering & Strategy Fundamentals.",     icon: <JuniorInnovatorsIcon /> },
  { title: "YOUNG ENGINEERS",   text: "Advanced Wireless & Autonomous Control.",  icon: <YoungEngineersIcon /> },
  { title: "ROBO MINDS",        text: "Elite Professional Sports & Robotics.",    icon: <RoboMindsIcon /> },
];

export default function Categories() {
  return (
    <section className="section categories">
      <div className="container">
        <h2 className="section-heading categories__title">Categories</h2>
        <div className="categories__grid">
          {cards.map((c) => (
            <div className={`cat-card ${c.highlight ? "cat-card--active" : ""}`} key={c.title}>
              <div className="cat-card__icon">{c.icon}</div>
              <h3>{c.title}</h3>
              <p>{c.text}</p>
              <a href="#" className="cat-card__link">
                LEARN MORE <FaArrowRight size={12} />
              </a>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
