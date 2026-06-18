import React, { useEffect, useState } from "react";
import "./CompetitionsEvents.css";
import { apiFetch } from "../api/api";

export default function CompetitionsEvents() {
  const [live, setLive]         = useState(null);
  const [upcoming, setUpcoming] = useState([]);
  const [past, setPast]         = useState([]);

  useEffect(() => {
    apiFetch("/api/events/public/live")
      .then((d) => setLive(d?.[0] || null))
      .catch(() => {});
    apiFetch("/api/events/public/upcoming")
      .then((d) => setUpcoming(d || []))
      .catch(() => {});
    apiFetch("/api/events/public/past")
      .then((d) => setPast(d || []))
      .catch(() => {});
  }, []);

  return (
    <section className="section comp">
      <div className="comp__glow" aria-hidden="true" />
      <div className="container">
        <h2 className="section-heading comp__title">Competitions &amp; Events</h2>

        <div className="comp__grid">
          {/* LIVE NOW */}
          <div className="comp__col">
            <h3 className="comp__col-title comp__col-title--red">Live Now</h3>
            {live ? (
              <div className="comp__card">
                <div className="comp__card-head">
                  <div>
                    <h4>{live.title}</h4>
                    <p className="comp__muted">{live.discipline}</p>
                  </div>
                  <span className="comp__tag">Ongoing</span>
                </div>
                <div className="comp__divider" />
                <BracketTree />
              </div>
            ) : (
              <div className="comp__card"><p className="comp__muted">No live events</p></div>
            )}
          </div>

          {/* UPCOMING */}
          <div className="comp__col">
            <h3 className="comp__col-title">Upcoming</h3>
            {upcoming.length ? upcoming.map((ev) => (
              <div className="comp__card comp__card--event" key={ev.id}>
                <h4>{ev.title}</h4>
                <div className="comp__meta">
                  <div>
                    <span className="comp__meta-label">Date</span>
                    <span>{ev.eventDate}</span>
                  </div>
                  <div>
                    <span className="comp__meta-label">Location</span>
                    <span>{ev.location}</span>
                  </div>
                  <div>
                    <span className="comp__meta-label">Category</span>
                    <span>{ev.category}</span>
                  </div>
                </div>
                <button className="btn btn-primary btn-block">REGISTER</button>
              </div>
            )) : (
              <div className="comp__card"><p className="comp__muted">No upcoming events</p></div>
            )}
          </div>

          {/* PAST RESULTS */}
          <div className="comp__col">
            <h3 className="comp__col-title">Past Results</h3>
            <div className="comp__card comp__card--results">
              {past.length ? past.map((r, i) => (
                <div key={i} className={`comp__result ${i < past.length - 1 ? "has-divider" : ""}`}>
                  <h4>{r.title}</h4>
                  <p className="comp__muted">{r.city}</p>
                </div>
              )) : (
                <p className="comp__muted">No past events</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

function BracketTree() {
  const slot = (x, y) => (
    <rect key={`${x}-${y}`} x={x} y={y} width="78" height="26" rx="5" fill="#3a3a3a" />
  );
  const line = (d) => <path d={d} stroke="var(--red)" strokeWidth="1.5" fill="none" />;
  return (
    <svg className="bracket" viewBox="0 0 280 200">
      {slot(0,10)}{slot(0,50)}{slot(0,120)}{slot(0,160)}
      {slot(95,30)}{slot(95,140)}
      {slot(190,85)}
      {line("M78,23 H88 V43 H95")}{line("M78,63 H88 V43")}
      {line("M78,133 H88 V153 H95")}{line("M78,173 H88 V153")}
      {line("M173,43 H183 V98 H190")}{line("M173,153 H183 V98")}
    </svg>
  );
}
