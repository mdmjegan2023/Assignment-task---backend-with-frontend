import React, { useEffect, useState } from "react";
import "./Sponsors.css";
import { apiFetch } from "../api/api";

const FALLBACK = [
  { name: "NIT Delhi",       logoUrl: "/assets/logo-nitdelhi.png" },
  { name: "Indian Bit",      logoUrl: "/assets/logo-indianbit.png" },
  { name: "NIT Silchar",     logoUrl: "/assets/logo-nitsilchar.png" },
  { name: "Delhivery",       logoUrl: "/assets/logo-delhivery.png" },
  { name: "IIT Bombay",      logoUrl: "/assets/logo-iitbombay.png" },
  { name: "General Robotics",logoUrl: "/assets/logo-generalrobotics.png" },
];

export default function Sponsors() {
  const [sponsors, setSponsors] = useState(FALLBACK);

  useEffect(() => {
    apiFetch("/api/sponsors")
      .then((data) => data?.length && setSponsors(data))
      .catch(() => {/* keep fallback */});
  }, []);

  return (
    <section className="section sponsors">
      <div className="container">
        <h2 className="section-heading sponsors__title">Sponsors</h2>
        <div className="sponsors__grid">
          {sponsors.map((s, i) => (
            <div className="sponsor" key={i}>
              <div className="sponsor__logo-wrap">
                <img src={s.logoUrl} alt={s.name} className="sponsor__logo" />
              </div>
              <span className="sponsor__name">{s.name}</span>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
