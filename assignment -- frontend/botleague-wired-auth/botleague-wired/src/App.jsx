import React from "react";
import Navbar from "./components/Navbar.jsx";
import Hero from "./components/Hero.jsx";
import CompetitionsEvents from "./components/CompetitionsEvents.jsx";
import UserJourney from "./components/UserJourney.jsx";
import WhatIsBotLeague from "./components/WhatIsBotLeague.jsx";
import Categories from "./components/Categories.jsx";
import Disciplines from "./components/Disciplines.jsx";
import WhyRegister from "./components/WhyRegister.jsx";
import JoinEcosystem from "./components/JoinEcosystem.jsx";
import Sponsors from "./components/Sponsors.jsx";
import Footer from "./components/Footer.jsx";

export default function App() {
  return (
    <>
      <Navbar />
      <Hero />
      <CompetitionsEvents />
      <UserJourney />
      <WhatIsBotLeague />
      <Categories />
      <Disciplines />
      <WhyRegister />
      <JoinEcosystem />
      <Sponsors />
      <Footer />
    </>
  );
}
