import React from "react";
import { FaMedal, FaGavel, FaBriefcase, FaBolt, FaUserAlt, FaStar, FaShieldAlt } from "react-icons/fa";
import "./WhyRegister.css";

const reasons = [
  { icon: <FaMedal />,    title: "NATIONAL RECOGNITION", text: "Benchmark your skills on India's official robotics leaderboard." },
  { icon: <FaGavel />,    title: "FAIR JUDGING",          text: "Compete with confidence under standardized, expert-led evaluation." },
  { icon: <FaBriefcase />,title: "CAREER OPS",            text: "Bridge the gap between arena victories and top-tier tech placements." },
  { icon: <FaBolt />,     title: "HIGH - ENERGY ECO",     text: "Join a nationwide community of elite innovators and robotics athletes." },
];

const rows = [
  { rank: "02", color: "blue",  score: "22000" },
  { rank: "03", color: "blue",  score: "20030" },
  { rank: "04", color: "pink",  score: "19500" },
  { rank: "05", color: "pink",  score: "15060" },
  { rank: "06", color: "pink",  score: "13865" },
  { rank: "07", color: "pink",  score: "10954" },
  { rank: "08", color: "pink",  score: "9057" },
];

export default function WhyRegister() {
  return (
    <section className="section whyreg">
      <div className="container whyreg__grid">
        <div className="whyreg__left">
          <span className="section-eyebrow">WHY REGISTER ?</span>
          <h2 className="section-heading">The League Advantage</h2>
          <div className="whyreg__list">
            {reasons.map((r) => (
              <div className="whyreg__row" key={r.title}>
                <div className="whyreg__icon">{r.icon}</div>
                <div>
                  <h4>{r.title}</h4>
                  <p>&quot;{r.text}&quot;</p>
                </div>
              </div>
            ))}
          </div>
        </div>

        <div className="whyreg__right">
          <div className="leaderboard">
            <div className="leaderboard__circuit" aria-hidden="true">
              <img src="/assets/circuit-bg.png" alt="" className="lb-circuit-img" />
            </div>
            <h3 className="leaderboard__title">LEADERBOARD</h3>
            <div className="leaderboard__top">
              <div className="leaderboard__avatar leaderboard__avatar--gold">
                <FaUserAlt />
                <span className="leaderboard__badge"><FaStar size={9} /></span>
              </div>
              <p className="leaderboard__top-name">#01 - Player Name</p>
              <p className="leaderboard__top-score">508754</p>
            </div>
            <div className="leaderboard__rows">
              {rows.map((r) => (
                <div className="lb-row" key={r.rank}>
                  <span className="lb-row__rank">{r.rank}</span>
                  <span className={`lb-row__avatar lb-row__avatar--${r.color}`}>
                    <FaUserAlt size={11} />
                  </span>
                  <span className="lb-row__name">Player Name</span>
                  <span className="lb-row__score">{r.score}</span>
                  <span className={`lb-row__shield lb-row__shield--${r.color}`}>
                    <FaShieldAlt size={11} />
                  </span>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
