import React from "react";
import { FaYoutube, FaInstagram, FaFacebookF, FaTwitter } from "react-icons/fa";
import "./Footer.css";

const col1 = ["The Arena", "Episodes", "National Rankings", "Programs", "Rulebooks"];
const col2 = ["Join the Team", "Sponsorships", "Help Center", "Contact Us", "Legal"];

export default function Footer() {
  return (
    <footer className="footer">
      <div className="container footer__inner">
        <div className="footer__links">
          <h4>QUICK LINKS</h4>
          <div className="footer__cols">
            <ul>
              {col1.map((l) => (
                <li key={l}>
                  <a href="#">{l}</a>
                </li>
              ))}
            </ul>
            <ul>
              {col2.map((l) => (
                <li key={l}>
                  <a href="#">{l}</a>
                </li>
              ))}
            </ul>
          </div>
        </div>

        <div className="footer__social">
          <h4>SOCIAL MEDIA</h4>
          <div className="footer__icons">
            <a href="#" aria-label="YouTube"><FaYoutube /></a>
            <a href="#" aria-label="Instagram"><FaInstagram /></a>
            <a href="#" aria-label="Facebook"><FaFacebookF /></a>
            <a href="#" aria-label="Twitter"><FaTwitter /></a>
          </div>
        </div>
      </div>
      <div className="container footer__bottom">
        <span>© {new Date().getFullYear()} BotLeague. All rights reserved.</span>
      </div>
    </footer>
  );
}
