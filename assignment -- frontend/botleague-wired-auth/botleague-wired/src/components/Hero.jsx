import React from "react";
import { FaCircle, FaPlay } from "react-icons/fa";
import "./Hero.css";

export default function Hero() {
  return (
    <section className="hero">
      <div className="hero__art" aria-hidden="true">
        <img
          src="/assets/hero-bg.jpg"
          alt="Robotics arena battle"
          className="hero__photo"
        />
      </div>
      <div className="hero__scrim" />
      <div className="container hero__content">
        <div className="hero__live">
          <FaCircle className="hero__live-dot" />
          <span>
            <strong>LIVE</strong> : Episode 14 . Bengaluru Regionals
          </span>
          <a href="#" className="hero__watch">
            <FaPlay size={10} /> WATCH LIVE
          </a>
        </div>
        <h1 className="hero__title">
          INDIA&apos;S ULTIMATE
          <br />
          ROBOTICS ARENA
        </h1>
        <p className="hero__subtitle">
          Build. Compete. Rank. The National Ecosystem for Robotics Arena
        </p>
        <div className="hero__actions">
          <button className="btn btn-primary">CREATE ACCOUNT</button>
          <button className="btn btn-outline">EXPLORE EVENTS</button>
        </div>
      </div>
    </section>
  );
}
