import React from "react";
import "./WhatIsBotLeague.css";

const items = [
  { num: "1.", title: "STRUCTURED EVENTS",  text: "From one-off events to a year-round competitive season." },
  { num: "2.", title: "DIGITAL IDENTITY",    text: "Your professional robotics legacy, tracked and verified." },
  { num: "3.", title: "NATIONAL RANKING",    text: "Benchmark your skills against the best engineers in India." },
  { num: "4.", title: "CAREER PATHWAY",      text: "Turning arena victories into real-world industry opportunities." },
];

export default function WhatIsBotLeague() {
  return (
    <section className="section whatis">
      <div className="container whatis__grid">
        <div className="whatis__left">
          <h2 className="section-heading whatis__title">What is BotLeague?</h2>
          <div className="whatis__list">
            {items.map((it) => (
              <div className="whatis__item" key={it.num}>
                <span className="whatis__num">{it.num}</span>
                <h4>{it.title}</h4>
                <p>&quot;{it.text}&quot;</p>
              </div>
            ))}
          </div>
        </div>

        <div className="whatis__right">
          <img
            src="/assets/whatis-illustration.png"
            alt="BotLeague tech illustration"
            className="whatis__photo"
          />
        </div>
      </div>
    </section>
  );
}
