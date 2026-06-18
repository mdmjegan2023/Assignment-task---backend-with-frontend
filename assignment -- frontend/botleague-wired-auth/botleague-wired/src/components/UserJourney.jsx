import React from "react";
import "./UserJourney.css";

// Custom SVG icons matching the original image style
const BuildTeamIcon = () => (
  <svg viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
    <circle cx="12" cy="10" r="4"/>
    <circle cx="22" cy="10" r="4"/>
    <path d="M4 26c0-4.4 3.6-8 8-8h8c4.4 0 8 3.6 8 8"/>
    <path d="M18 14c1.2.6 2.5 1 4 1"/>
  </svg>
);

const CompeteIcon = () => (
  <svg viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
    <rect x="4" y="6" width="24" height="18" rx="2"/>
    <path d="M16 4v4M8 24l2-4h12l2 4M11 14h10M16 10v8"/>
    <circle cx="16" cy="15" r="3"/>
  </svg>
);

const RankingIcon = () => (
  <svg viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
    <path d="M16 4l3 6 7 1-5 5 1 7-6-3-6 3 1-7-5-5 7-1z"/>
    <path d="M8 28h16M12 24v4M16 22v6M20 24v4"/>
  </svg>
);

const JoinLeagueIcon = () => (
  <svg viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
    <path d="M28 16H4M20 8l8 8-8 8"/>
    <circle cx="10" cy="16" r="4"/>
  </svg>
);

const steps = [
  { num: "STEP 1", title: "BUILD YOUR\nTEAM",               icon: <BuildTeamIcon /> },
  { num: "STEP 2", title: "COMPETE ACROSS\nINDIA",          icon: <CompeteIcon /> },
  { num: "STEP 3", title: "EARN NATIONAL\nRANKING & VALUE", icon: <RankingIcon /> },
  { num: "STEP 4", title: "JOIN THE\nLEAGUE",               icon: <JoinLeagueIcon /> },
];

export default function UserJourney() {
  return (
    <section className="section journey">
      <div className="container">
        <div className="journey__head">
          <span className="section-eyebrow">USER JOURNEY</span>
          <h2 className="section-heading">Your Path To The League</h2>
          <p className="journey__sub">Lorem Ipsum Lorem Ipsum Lorem Ipsum</p>
        </div>
        <div className="journey__row">
          {steps.map((s, i) => (
            <React.Fragment key={s.num}>
              <div className="journey__step">
                <div className="journey__circle">{s.icon}</div>
                <div className="journey__stem" />
                <span className="journey__num">{s.num}</span>
                <h4 className="journey__title">
                  {s.title.split("\n").map((line, idx) => (
                    <React.Fragment key={idx}>{line}<br /></React.Fragment>
                  ))}
                </h4>
              </div>
              {i < steps.length - 1 && <div className="journey__connector" />}
            </React.Fragment>
          ))}
        </div>
      </div>
    </section>
  );
}
