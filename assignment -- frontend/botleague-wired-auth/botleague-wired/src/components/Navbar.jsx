import React, { useState, useEffect } from "react";
import { FaBars, FaTimes, FaUser } from "react-icons/fa";
import "./Navbar.css";
import AuthModal from "./AuthModal";

const NAV_LINKS = ["Events", "Programs", "Community", "Ranks"];

export default function Navbar() {
  const [open, setOpen]       = useState(false);
  const [modal, setModal]     = useState(null); // "login" | "register" | null
  const [user, setUser]       = useState(null);

  // Load user from localStorage on mount and on auth-change event
  useEffect(() => {
    const load = () => {
      const stored = localStorage.getItem("user");
      setUser(stored ? JSON.parse(stored) : null);
    };
    load();
    window.addEventListener("auth-change", load);
    return () => window.removeEventListener("auth-change", load);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    setUser(null);
    window.dispatchEvent(new Event("auth-change"));
  };

  return (
    <>
      <header className="navbar">
        <div className="container navbar__inner">
          <a href="#" className="navbar__logo" aria-label="BotLeague home">
            <span className="navbar__tri navbar__tri--tl" />
            <span className="navbar__logo-text">
              <span className="navbar__logo-bot">
                B
                <span className="navbar__logo-gear" aria-hidden="true">
                  <svg viewBox="0 0 24 24" width="0.85em" height="0.85em">
                    <g fill="currentColor">
                      <circle cx="12" cy="12" r="3.4" />
                      {Array.from({ length: 8 }).map((_, i) => (
                        <rect key={i} x="10.6" y="0.5" width="2.8" height="5.5" rx="0.8"
                          transform={`rotate(${i * 45} 12 12)`} />
                      ))}
                    </g>
                  </svg>
                </span>
                T
              </span>
              <span className="navbar__logo-league">LEAGUE</span>
            </span>
            <span className="navbar__tri navbar__tri--br" />
          </a>

          <nav className={`navbar__links ${open ? "is-open" : ""}`}>
            {NAV_LINKS.map((link, i) => (
              <a key={link} href="#" className={`navbar__link ${i === 0 ? "is-active" : ""}`}
                onClick={() => setOpen(false)}>{link}</a>
            ))}
            <div className="navbar__cta navbar__cta--mobile">
              {user ? (
                <UserMenu user={user} onLogout={handleLogout} />
              ) : (
                <>
                  <button className="btn btn-outline" onClick={() => { setModal("login"); setOpen(false); }}>LOGIN</button>
                  <button className="btn btn-primary" onClick={() => { setModal("register"); setOpen(false); }}>REGISTER NOW</button>
                </>
              )}
            </div>
          </nav>

          <div className="navbar__cta">
            {user ? (
              <UserMenu user={user} onLogout={handleLogout} />
            ) : (
              <>
                <button className="btn btn-outline" onClick={() => setModal("login")}>LOGIN</button>
                <button className="btn btn-primary" onClick={() => setModal("register")}>REGISTER NOW</button>
              </>
            )}
          </div>

          <button className="navbar__burger" onClick={() => setOpen((o) => !o)} aria-label="Toggle menu">
            {open ? <FaTimes /> : <FaBars />}
          </button>
        </div>
      </header>

      {modal && <AuthModal defaultTab={modal} onClose={() => setModal(null)} />}
    </>
  );
}

function UserMenu({ user, onLogout }) {
  const [open, setOpen] = useState(false);
  const initials = (user.fullName || user.username || "U").charAt(0).toUpperCase();

  return (
    <div style={{ position: "relative" }}>
      <div className="auth-modal__user" style={{ cursor: "pointer" }} onClick={() => setOpen((o) => !o)}>
        <div className="auth-modal__avatar">{initials}</div>
        <div>
          <div className="auth-modal__username">{user.username}</div>
          <div className="auth-modal__role">{user.roles?.includes("ADMIN") ? "Admin" : "Member"}</div>
        </div>
      </div>
      {open && (
        <div style={{
          position: "absolute", right: 0, top: "48px",
          background: "#1a1a1a", border: "1px solid rgba(255,255,255,0.1)",
          borderRadius: "8px", padding: "8px 0", minWidth: "160px", zIndex: 100,
        }}>
          <div style={{ padding: "8px 16px", fontSize: "12px", color: "#9a9a9a", borderBottom: "1px solid rgba(255,255,255,0.07)" }}>
            {user.email}
          </div>
          <button onClick={onLogout} style={{
            width: "100%", background: "transparent", color: "#ff5a5f",
            padding: "10px 16px", textAlign: "left", fontSize: "14px", cursor: "pointer", border: "none",
          }}>
            Logout
          </button>
        </div>
      )}
    </div>
  );
}
