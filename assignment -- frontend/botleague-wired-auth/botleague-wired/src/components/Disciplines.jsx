import React from "react";
import "./Disciplines.css";

const row1 = [
  { title: "Robo Race",    img: "/assets/disc-roborace.jpg" },
  { title: "Line Follower", img: "/assets/disc-linefollower.jpg" },
  { title: "RC Racing",    img: "/assets/disc-rcracing.jpg" },
  { title: "FPV Drone Racing & Aeromodelling", img: "/assets/disc-drone.jpg" },
];

const row2 = [
  { title: "Robo Hockey",  img: "/assets/disc-robohockey.jpg" },
  { title: "Robo War",     img: "/assets/disc-robowar.png" },
];

function DisciplineCard({ d }) {
  return (
    <div className="disc-card" key={d.title}>
      <div className="disc-card__art">
        <img src={d.img} alt={d.title} className="disc-card__img" />
        <div className="disc-card__overlay" />
      </div>
      <div className="disc-card__label">{d.title}</div>
    </div>
  );
}

export default function Disciplines() {
  return (
    <section className="section disciplines">
      <div className="container">
        <span className="section-eyebrow">SPORTS</span>
        <h2 className="section-heading disciplines__title">Competition Disciplines</h2>
        <div className="disc__grid">
          {row1.map((d) => <DisciplineCard key={d.title} d={d} />)}
        </div>
        <div className="disc__row2">
          {row2.map((d) => <DisciplineCard key={d.title} d={d} />)}
        </div>
      </div>
    </section>
  );
}
